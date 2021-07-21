package com.dongtech.service.loan;

import com.dongtech.model.loan.IncomeRecord;

import java.util.List;
import java.util.Map;


public interface IncomeRecordService {
	
	/**
	 * 查询用户的收益记录总数
	 * 
	 * @return
	 */
	public int getIncomeRecordByTotal(Map<String, Object> paramMap);

	/**
	 * 分页查询用户收益数据
	 * 
	 * @param uid
	 * @param limit
	 * @return
	 */
	public List<IncomeRecord> getIncomeRecordByPage(Map<String, Object> paramMap);
	
	/**
	 * 添加收益计划记录
	 * 
	 * @param incomeRecord
	 * @return
	 */
	public int addIncomeRecord(IncomeRecord incomeRecord);
	
	/**
	 * 查询收益时间是当天的数据
	 * 便于为用户回款
	 * 
	 * @return
	 */
	public List<IncomeRecord> getIncomeRecordByIncomeDateIsCurrentDate();
	
	/**
	 * 根据主键ID更新收益计划数据
	 * 
	 * @param incomeRecord
	 * @return
	 */
	public int updateIncomeRecordById(IncomeRecord incomeRecord);
}
