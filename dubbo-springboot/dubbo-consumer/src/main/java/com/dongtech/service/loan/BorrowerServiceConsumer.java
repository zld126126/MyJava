package com.dongtech.service.loan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dongtech.model.loan.BorrowerInfo;
import com.dongtech.service.loan.BorrowerService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BorrowerServiceConsumer {

	@Reference
	BorrowerService borrowerService;

	public List<BorrowerInfo> getBorrowerByPage(Map<String, Object> paramMap){
		return borrowerService.getBorrowerByPage(paramMap);
	}
	
	public int getBorrowerInfoByTotal(){
		return borrowerService.getBorrowerInfoByTotal();
	}
}
