package com.dongtech.service.loan;

import com.dongtech.model.loan.DealRecord;

import java.util.List;
/**
 * 交易记录相关处理Service
 * 
 * @author 东宝
 *
 */
public interface DealRecordService {

	/**
	 * 保存交易记录
	 * 
	 * @param dealRecord
	 * @return
	 */
	public int addDealRecord(DealRecord dealRecord);
	
	/**
	 * 根据用户id、交易类型、限制条数查询交易数据
	 * 
	 * @param uid
	 * @param dealType
	 * @param limit
	 * @return
	 */
	public List<DealRecord> getDealRecordByUid(int uid, int dealType, int limit);
	
	/**
	 * 根据用户id、交易类型、分页查询交易记录总数
	 * 
	 * @param uid
	 * @param dealType 0表示查询所有
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<DealRecord> getDealRecordByCount(int uid, int dealType, int currentPage, int pageSize);
	
	/**
	 * 根据用户id、交易类型、分页查询交易数据
	 * 
	 * @param uid
	 * @param dealType 0表示查询所有
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<DealRecord> getDealRecordByPage(int uid, int dealType, int currentPage, int pageSize);
}