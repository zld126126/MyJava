package com.dongtech.service.loan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dongtech.model.loan.RechargeRecord;
import com.dongtech.service.loan.RechargeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 充值处理Service接口
 * 
 * @author 东宝
 *
 */
@Component
public class RechargeServiceConsumer {

	@Reference
	RechargeService rechargeService;
	
	/**
	 * 添加充值订单记录
	 * 
	 * @param rechargeRecord
	 * @return
	 */
	public int addRechargeRecord(RechargeRecord rechargeRecord){
		return rechargeService.addRechargeRecord(rechargeRecord);
	}

	/**
	 * 充值
	 * 
	 * @param tradeNo
	 * @param rechargeMoney
	 * @param status
	 * @return
	 */
	public int recharge(String tradeNo, Double rechargeMoney, int status){
		return rechargeService.recharge(tradeNo,rechargeMoney,status);
	}
	
	/**
	 * 查询充值记录总数
	 * 
	 * @return
	 */
	public int getRechargeRecordByCount(int uid){
		return rechargeService.getRechargeRecordByCount(uid);
	}
	
	/**
	 * 分页查询充值记录列表
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<RechargeRecord> getRechargeRecordByPage(Map<String, Object> paramMap){
		return rechargeService.getRechargeRecordByPage(paramMap);
	}
}