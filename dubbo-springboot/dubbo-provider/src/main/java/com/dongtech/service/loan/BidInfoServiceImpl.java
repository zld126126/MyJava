package com.dongtech.service.loan;

import com.alibaba.dubbo.config.annotation.Service;
import com.dongtech.constants.Constants;
import com.dongtech.mapper.loan.BidInfoMapper;
import com.dongtech.mapper.loan.LoanInfoMapper;
import com.dongtech.mapper.user.FinanceAccountMapper;
import com.dongtech.model.common.ReturnObject;
import com.dongtech.model.loan.BidInfo;
import com.dongtech.model.loan.LoanInfo;
import com.dongtech.model.user.TopUser;
import com.dongtech.service.loan.BidInfoService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 投标处理相关Service实现
 * 
 * @author 东宝
 *
 */
@Service
public class BidInfoServiceImpl implements BidInfoService {
	
	private static final Logger logger = LogManager.getLogger(BidInfoServiceImpl.class);
	
	@Autowired
	private BidInfoMapper bidInfoMapper;
	
	@Autowired
	private LoanInfoMapper loanInfoMapper;
	
	@Autowired
	private FinanceAccountMapper financeAccountMapper;

	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	
	/**
	 * 投标入库
	 * 
	 * @param paramMap
	 * @return ReturnObject
	 */
	public ReturnObject addBidInfo (Map<String, Object> paramMap) {
		
		ReturnObject returnObject = new ReturnObject();
		returnObject.setErrorCode("0");//0表示成功，1表示失败
		returnObject.setErrorMessage("投资成功");
		
		boolean flag = true;
		Integer loanId = (Integer)paramMap.get("loanId");
		//先获取一下产品信息
		LoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(loanId);
		
		//该产品已经投资满标了，不能再投资了
		if (loanInfo.getLeftProductMoney() <= 0) {
			returnObject.setErrorCode("1");//0表示成功，1表示失败
			returnObject.setErrorMessage("该产品已投资满标了，请换个产品投资吧~");
			return returnObject;
		}
		//产品版本号
		paramMap.put("version", loanInfo.getVersion());
		//减库存（数据库乐观锁避免超卖）
		int updateLoan = loanInfoMapper.updateLoanInfo(paramMap);
		
		if (updateLoan > 0) {//库存更新成功
			
			//查询是否已经投资满标，若满标更新产品状态
			LoanInfo loan = loanInfoMapper.selectByPrimaryKey(loanId);
			if (loan.getLeftProductMoney() <= 0) {
				LoanInfo upLoanInfo = new LoanInfo();
				upLoanInfo.setId(loan.getId());
				upLoanInfo.setProductFullTime(new Date());
				upLoanInfo.setProductStatus(1);//产品状态（0未满标，1已满标，2满标已生成收益计划）
				int upProductFull = loanInfoMapper.updateByPrimaryKeySelective(upLoanInfo);
				logger.info("更新产品为满标状态，loanId=" + loanId + ", upProductFull=" + upProductFull);
			}
			//账户扣款
			int updateFinance = financeAccountMapper.updateFinanceAccount(paramMap);
			if (updateFinance > 0) {
				//插入投标记录
				BidInfo bidInfo = new BidInfo();
				bidInfo.setBidMoney((Double)paramMap.get("bidMoney"));
				bidInfo.setBidStatus(1);
				bidInfo.setUid((Integer)paramMap.get("uid"));
				bidInfo.setBidTime(new Date());
				bidInfo.setLoanId((Integer)paramMap.get("loanId"));
				int insertBid = bidInfoMapper.insertSelective(bidInfo);
				if (insertBid <= 0) {
					flag = false;
					returnObject.setErrorCode("1");//0表示成功，1表示失败
					returnObject.setErrorMessage("投资失败");
				} else {
					//为投资排行榜记录数据
					String phone = (String)paramMap.get("phone");
					Double bidMoney = (Double)paramMap.get("bidMoney");
					redisTemplate.opsForZSet().incrementScore(Constants.INVEST_TOP, phone, bidMoney);
				}
			} else {
				flag = false;
				returnObject.setErrorCode("1");//0表示成功，1表示失败
				returnObject.setErrorMessage("投资失败");
			}
		} else {
			flag = false;
			returnObject.setErrorCode("1");//0表示成功，1表示失败
			returnObject.setErrorMessage("投资失败");
		}
		if (!flag) {
			//抛出异常，回滚事务，投标失败
			throw new RuntimeException();
		}
		return returnObject;
	}
	
	/**
	 * 获取平台总投资金额
	 * 
	 * @return
	 */
	public Double getAllBidMoney () {
		//先去redis缓存，若缓存不存在，再取数据库，减少对数据库的访问，提升性能
		BoundValueOperations<String, Serializable> boundValueOperations = redisTemplate.boundValueOps(Constants.ALL_BID_MONEY);
		Double allBidMoney = (Double)boundValueOperations.get();
		if (null == allBidMoney) {
			allBidMoney = bidInfoMapper.getAllBidMoney();
			boundValueOperations.set(allBidMoney);
			boundValueOperations.expire(15, TimeUnit.MINUTES);
		}
		return allBidMoney;
	}
	
	/**
	 * 根据标id查询出该标的投资记录列表
	 * 
	 * @param loanId
	 * @return
	 */
	public List<BidInfo> getBidInfoByLoanId (int loanId) {
		return bidInfoMapper.selectBidInfoByLoanId(loanId);
	}
	
	/**
	 * 查询用户的投标记录总数
	 * 
	 * @return
	 */
	public int getBidInfoByTotal(Map<String, Object> paramMap) {
		return bidInfoMapper.getBidInfoByTotal(paramMap);
	}
	
	/**
	 * 分页查询用户投标记录
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<BidInfo> getBidInfoByPage (Map<String, Object> paramMap) {
		return bidInfoMapper.getBidInfoByPage(paramMap);
	}
	
	/**
	 * 获取投资排行榜
	 * 
	 * @return
	 */
	public List<TopUser> getBidTop () {
		List<TopUser> topUserList = new ArrayList<TopUser>();
		
		Set<TypedTuple<Serializable>> set = redisTemplate.opsForZSet().reverseRangeWithScores(Constants.INVEST_TOP, 0, 9);
		Iterator<TypedTuple<Serializable>> iterator = set.iterator();
		while (iterator.hasNext()) {
			TypedTuple<Serializable> typedTuple = iterator.next();
			
			TopUser topUser = new TopUser();
			topUser.setPhone((String)typedTuple.getValue());
			topUser.setScore(typedTuple.getScore());
			
			topUserList.add(topUser);
		}
		return topUserList;
	}
}
