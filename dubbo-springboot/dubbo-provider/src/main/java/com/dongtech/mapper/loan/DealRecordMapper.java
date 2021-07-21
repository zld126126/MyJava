package com.dongtech.mapper.loan;

import com.dongtech.model.loan.DealRecord;

import java.util.List;
import java.util.Map;


public interface DealRecordMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(DealRecord record);

    int insertSelective(DealRecord record);

    DealRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DealRecord record);

    int updateByPrimaryKey(DealRecord record);
    
    List<DealRecord> getDealRecordByUid(Map<String, Object> paramMap);
    
}