package com.dongtech.mapper.loan;

import com.dongtech.model.loan.BidInfo;

import java.util.List;
import java.util.Map;


public interface BidInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);
    
    /**
     * 投资插入投标数据
     * 
     * @param record
     * @return
     */
    int insertSelective(BidInfo bidInfo);

    BidInfo selectByPrimaryKey(Integer id);
    
    /**
	 * 根据标id查询出该标的投资记录列表
	 * 
	 * @param loanId
	 * @return
	 */
    List<BidInfo> selectBidInfoByLoanId(int loanId);
    
    Double getAllBidMoney();

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);
    
    /**
	 * 查询用户的投标记录总数
	 * 
	 * @return
	 */
    int getBidInfoByTotal(Map<String, Object> paramMap);
    
    /**
     * 分页查询用户投标记录
     * 
     * @param paramMap
     * @return
     */
    List<BidInfo> getBidInfoByPage(Map<String, Object> paramMap);
}