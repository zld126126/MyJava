package com.dongtech.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dongtech.model.loan.LoanInfo;
import com.dongtech.service.loan.BidInfoServiceConsumer;
import com.dongtech.service.loan.LoanInfoServiceConsumer;
import com.dongtech.service.user.UserServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页处理Controller
 * 
 * @author 东宝
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private UserServiceConsumer userService;
	
	@Autowired
	private LoanInfoServiceConsumer loanInfoService;
	
	@Autowired
	private BidInfoServiceConsumer bidInfoService;

	/**
	 * 首页页面数据展示处理
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index (Model model) {
		
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("currentPage", 0);
		paramMap.put("pageSize", 1);
		paramMap.put("productType", 0);
		//获取新手宝产品
		List<LoanInfo> xLoanInfoList = loanInfoService.getLoanInfoByPage(paramMap);

		paramMap.put("pageSize", 4);
		paramMap.put("productType", 1);
		//获取优选产品
		List<LoanInfo> uLoanInfoList = loanInfoService.getLoanInfoByPage(paramMap);

		paramMap.put("pageSize", 8);
		paramMap.put("productType", 2);
		//获取散标产品
		List<LoanInfo> sLoanInfoList = loanInfoService.getLoanInfoByPage(paramMap);

		//历史年化收益率
		Double historyAverageRate = loanInfoService.getHistoryAverageRate();

		//获取平台总注册用户数
		Long allUserCount = userService.getAllUserCount();

		//获取平台总投资金额
		Double allBidMoney = bidInfoService.getAllBidMoney();

		model.addAttribute("xLoanInfoList", xLoanInfoList);
		model.addAttribute("uLoanInfoList", uLoanInfoList);
		model.addAttribute("sLoanInfoList", sLoanInfoList);

		model.addAttribute("historyAverageRate", historyAverageRate);
		model.addAttribute("allUserCount", allUserCount);
		model.addAttribute("allBidMoney", allBidMoney);
		
		return "index";
	}
	
	/**
	 * 用户和交易统计数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loan/stat")
	public @ResponseBody Map<String, Object> stat (Model model) {

		Map<String, Object> retMap = new ConcurrentHashMap<String, Object>();
		
		//获取平台总注册用户数
		Long allUserCount = userService.getAllUserCount();
		
		//获取平台总投资金额
		Double allBidMoney = bidInfoService.getAllBidMoney();
		
		retMap.put("allUserCount", allUserCount);
		retMap.put("allBidMoney", allBidMoney);
		return retMap;
	}
}
