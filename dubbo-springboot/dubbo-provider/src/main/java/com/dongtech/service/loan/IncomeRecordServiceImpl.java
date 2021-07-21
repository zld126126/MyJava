package com.dongtech.service.loan;

import com.alibaba.dubbo.config.annotation.Service;
import com.dongtech.mapper.loan.IncomeRecordMapper;
import com.dongtech.model.loan.IncomeRecord;
import com.dongtech.service.loan.IncomeRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 收益记录相关处理Service实现
 * 
 * @author 东宝
 *
 */
@Service
public class IncomeRecordServiceImpl implements IncomeRecordService {

	@Autowired
	private IncomeRecordMapper incomeRecordMapper;
	
	/**
	 * 查询用户的收益记录总数
	 * 
	 * @return
	 */
	public int getIncomeRecordByTotal(Map<String, Object> paramMap) {
		return incomeRecordMapper.getIncomeRecordByTotal(paramMap);
	}
	
	/**
	 * 分页查询用户收益数据
	 * 
	 * @param uid
	 * @param limit
	 * @return
	 */
	@Override
	public List<IncomeRecord> getIncomeRecordByPage (Map<String, Object> paramMap) {
		return incomeRecordMapper.getIncomeRecordByPage(paramMap);
	}
	
	/**
	 * 添加收益计划记录
	 * 
	 * @param incomeRecord
	 * @return
	 */
	@Override
	public int addIncomeRecord (IncomeRecord incomeRecord) {
		return incomeRecordMapper.insertSelective(incomeRecord);
	}
	
	/**
	 * 查询收益时间是当天的数据
	 * 便于为用户回款
	 * 
	 * @return
	 */
	public List<IncomeRecord> getIncomeRecordByIncomeDateIsCurrentDate() {
		return incomeRecordMapper.getIncomeRecordByIncomeDateIsCurrentDate();
	}
	
	/**
	 * 根据主键ID更新收益计划数据
	 * 
	 * @param incomeRecord
	 * @return
	 */
	public int updateIncomeRecordById (IncomeRecord incomeRecord) {
		return incomeRecordMapper.updateByPrimaryKeySelective(incomeRecord);
	}
}
