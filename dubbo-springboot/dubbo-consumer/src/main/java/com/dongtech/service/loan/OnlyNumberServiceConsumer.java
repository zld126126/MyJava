package com.dongtech.service.loan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dongtech.service.loan.OnlyNumberService;
import org.springframework.stereotype.Component;

/**
 * 生成全局唯一数字
 * 
 * @author 东宝
 *
 */
@Component
public class OnlyNumberServiceConsumer {

	@Reference
	OnlyNumberService onlyNumberService;

	/**
	 * 生成唯一数字
	 * 
	 * @return
	 */
	public Long getOnlyNumber(){
		return onlyNumberService.getOnlyNumber();
	}
	
}
