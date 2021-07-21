package com.dongtech.mapper.loan;

import com.dongtech.model.loan.RechargeRecord;

import java.util.List;
import java.util.Map;



public interface RechargeRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer id);
    
    RechargeRecord selectByRechargeNo(String rechargeNo);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);
    
    int updateRechargeStatus(Map<String, Object> paramMap);
    
    int selectByRechargeCount(Integer uid);
    
    List<RechargeRecord> selectByRechargePage(Map<String, Object> paramMap);
}