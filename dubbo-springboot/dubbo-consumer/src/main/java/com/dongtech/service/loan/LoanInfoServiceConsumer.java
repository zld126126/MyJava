package com.dongtech.service.loan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dongtech.model.loan.LoanInfo;
import com.dongtech.service.loan.LoanInfoService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LoanInfoServiceConsumer {

	@Reference
	LoanInfoService loanInfoService;

	/**
	 * 分页查询产品列表信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<LoanInfo> getLoanInfoByPage(Map<String, Object> paramMap){
		return loanInfoService.getLoanInfoByPage(paramMap);
	}
	
	/**
	 * 查询所有的产品总数
	 * 
	 * @return
	 */
	public int getLoanInfoByTotal(Map<String, Object> paramMap){
		return loanInfoService.getLoanInfoByTotal(paramMap);
	}
	
	/**
	 * 根据id查询投标产品详情
	 * 
	 * @param id
	 * @return
	 */
	public LoanInfo getLoanInfoById(int id){
		return loanInfoService.getLoanInfoById(id);
	}
	
	/**
	 * 根据状态获取产品及对应产品下的投标记录
	 * 
	 * @param productStatus
	 * @return
	 */
	public List<LoanInfo> getBidRecordForLoanInfo(Integer productStatus){
		return loanInfoService.getBidRecordForLoanInfo(productStatus);
	}
	
	/**
	 * 根据产品Id更新产品信息
	 * 
	 * @param loanInfo
	 * @return
	 */
	public int updateLoanInfoById(LoanInfo loanInfo){
		return loanInfoService.updateLoanInfoById(loanInfo);
	}
	
	/**
	 * 根据产品状态查询产品信息
	 * 
	 * @param productStatus
	 * @return
	 */
	public List<LoanInfo> getLoanInfoByProductStatus(int productStatus){
		return loanInfoService.getLoanInfoByProductStatus(productStatus);
	}
	
	/**
	 * 获取历史平均年化收益
	 * 
	 * @return
	 */
	public Double getHistoryAverageRate(){
		return loanInfoService.getHistoryAverageRate();
	}
}