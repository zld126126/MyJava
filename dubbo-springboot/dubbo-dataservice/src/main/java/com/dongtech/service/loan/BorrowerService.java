package com.dongtech.service.loan;

import java.util.List;
import java.util.Map;
import com.dongtech.model.loan.BorrowerInfo;

public interface BorrowerService {

	public List<BorrowerInfo> getBorrowerByPage(Map<String, Object> paramMap);
	
	public int getBorrowerInfoByTotal();
}
