package com.dongtech.service.loan;

import java.util.List;
import java.util.Map;
import com.dongtech.model.loan.RechargeRecord;

/**
 * 充值处理Service接口
 * 
 * @author 东宝
 *
 */
public interface RechargeService {
	
	/**
	 * 添加充值订单记录
	 * 
	 * @param rechargeRecord
	 * @return
	 */
	public int addRechargeRecord(RechargeRecord rechargeRecord);

	/**
	 * 充值
	 * 
	 * @param tradeNo
	 * @param rechargeMoney
	 * @param status
	 * @return
	 */
	public int recharge(String tradeNo, Double rechargeMoney, int status);
	
	/**
	 * 查询充值记录总数
	 * 
	 * @return
	 */
	public int getRechargeRecordByCount(int uid);
	
	/**
	 * 分页查询充值记录列表
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<RechargeRecord> getRechargeRecordByPage(Map<String, Object> paramMap);
}