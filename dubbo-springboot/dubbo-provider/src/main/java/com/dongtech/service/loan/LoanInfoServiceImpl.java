package com.dongtech.service.loan;

import com.alibaba.dubbo.config.annotation.Service;
import com.dongtech.constants.Constants;
import com.dongtech.mapper.loan.LoanInfoMapper;
import com.dongtech.model.loan.LoanInfo;
import com.dongtech.service.loan.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 产品信息相关处理Service实现
 * 
 * @author 东宝
 *
 */
@Service
public class LoanInfoServiceImpl implements LoanInfoService {

	@Autowired
	private LoanInfoMapper loanInfoMapper;

	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Override
	public List<LoanInfo> getLoanInfoByPage(Map<String, Object> paramMap) {
		return loanInfoMapper.getLoanInfoByPage(paramMap);
	}
	
	/**
	 * 查询所有的产品总数
	 * 
	 * @return
	 */
	public int getLoanInfoByTotal(Map<String, Object> paramMap) {
		return loanInfoMapper.getLoanInfoByTotal(paramMap);
	}

	/**
	 * 根据id查询投标产品详情
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public LoanInfo getLoanInfoById(int id) {
		return loanInfoMapper.getLoanInfoById(id);
	}
	
	/**
	 * 获取满标产品及对应产品下的投标记录
	 * 
	 * @return
	 */
	public List<LoanInfo> getBidRecordForLoanInfo(Integer productStatus) {
		return loanInfoMapper.getBidRecordForLoanInfo(productStatus);
	}
	
	/**
	 * 根据产品Id更新产品信息
	 * 
	 * @param loanInfo
	 * @return
	 */
	public int updateLoanInfoById (LoanInfo loanInfo) {
		return loanInfoMapper.updateByPrimaryKeySelective(loanInfo);
	}
	
	/**
	 * 根据产品状态查询产品信息
	 * 
	 * @param loanInfo
	 * @return
	 */
	public List<LoanInfo> getLoanInfoByProductStatus(int productStatus) {
		return loanInfoMapper.getLoanInfoByProductStatus(productStatus);
	}
	
	/**
	 * 获取历史平均年化收益
	 * 
	 * @return
	 */
	public Double getHistoryAverageRate() {
		//先去redis缓存，若缓存不存在，再取数据库，减少对数据库的访问，提升性能
		BoundValueOperations<String, Serializable> boundValueOperations = redisTemplate.boundValueOps(Constants.HISTORY_AVERAGE_RATE);
		Double historyAverageRate = (Double)boundValueOperations.get();
		if (null == historyAverageRate) {
			historyAverageRate = loanInfoMapper.getLoanInfoByHistoryAverageRate();
			boundValueOperations.set(historyAverageRate);
			boundValueOperations.expire(15, TimeUnit.MINUTES);
		}
		return historyAverageRate;
	}
}
