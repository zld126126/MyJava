package com.dongtech.service.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dongtech.model.user.FinanceAccount;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FinanceAccountServiceConsumer {

	@Reference
	FinanceAccountService financeAccountService;

	/**
	 * 根据用户ID查询用户财务资金信息
	 * 
	 * @param uid
	 * @return
	 */
	public FinanceAccount getFinanceAccountByUid(Long uid){
		return financeAccountService.getFinanceAccountByUid(uid);
	}
	
	/**
	 * 更新用户资金金额，收益回款操作
	 * 
	 * @param paramMap
	 * @return
	 */
	public int updateFinanceAccountByBidAndIncomeMoneyAdd(Map<String, Object> paramMap){
		return financeAccountService.updateFinanceAccountByBidAndIncomeMoneyAdd(paramMap);
	}
}
 