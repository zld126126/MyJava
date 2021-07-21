package com.dongtech.service.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.dongtech.mapper.user.FinanceAccountMapper;
import com.dongtech.model.user.FinanceAccount;
import com.dongtech.service.user.FinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 资金账户相关处理Service实现
 * 
 * @author 东宝
 *
 */
@Service
public class FinanceAccountServiceImpl implements FinanceAccountService {

	@Autowired
	private FinanceAccountMapper financeAccountMapper;
	
	@Override
	public FinanceAccount getFinanceAccountByUid(Long uid) {
		return financeAccountMapper.getFinanceAccountByUid(uid);
	}
	
	/**
	 * 更新用户资金金额，收益回款操作
	 * 
	 * @param paramMap
	 * @return
	 */
	public int updateFinanceAccountByBidAndIncomeMoneyAdd(Map<String, Object> paramMap) {
		return financeAccountMapper.updateFinanceAccountByBidAndIncomeMoneyAdd(paramMap);
	}
}