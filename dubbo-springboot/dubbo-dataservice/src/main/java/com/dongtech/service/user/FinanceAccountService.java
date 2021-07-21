package com.dongtech.service.user;

import com.dongtech.model.user.FinanceAccount;

import java.util.Map;

public interface FinanceAccountService {

	/**
	 * 根据用户ID查询用户财务资金信息
	 * 
	 * @param uid
	 * @return
	 */
	public FinanceAccount getFinanceAccountByUid(Long uid);
	
	/**
	 * 更新用户资金金额，收益回款操作
	 * 
	 * @param paramMap
	 * @return
	 */
	public int updateFinanceAccountByBidAndIncomeMoneyAdd(Map<String, Object> paramMap);
}
 