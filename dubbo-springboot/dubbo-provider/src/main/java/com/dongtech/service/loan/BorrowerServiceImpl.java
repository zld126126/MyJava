package com.dongtech.service.loan;

import com.alibaba.dubbo.config.annotation.Service;
import com.dongtech.model.loan.BorrowerInfo;
import com.dongtech.service.loan.BorrowerService;

import java.util.List;
import java.util.Map;

/**
 * 借款人相关处理Service实现
 * 
 * @author 东宝
 *
 */
@Service
public class BorrowerServiceImpl implements BorrowerService {

	@Override
	public List<BorrowerInfo> getBorrowerByPage(Map<String, Object> arg0) {
		return null;
	}

	@Override
	public int getBorrowerInfoByTotal() {
		return 0;
	}
}
