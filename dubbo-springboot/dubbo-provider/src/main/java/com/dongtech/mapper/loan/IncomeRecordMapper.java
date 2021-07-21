package com.dongtech.mapper.loan;

import com.dongtech.model.loan.IncomeRecord;

import java.util.List;
import java.util.Map;


public interface IncomeRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeRecord record);

    int insertSelective(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeRecord record);
    
    int getIncomeRecordByTotal(Map<String, Object> paramMap);

    List<IncomeRecord> getIncomeRecordByPage(Map<String, Object> paramMap);
    
    List<IncomeRecord> getIncomeRecordByIncomeDateIsCurrentDate();
}