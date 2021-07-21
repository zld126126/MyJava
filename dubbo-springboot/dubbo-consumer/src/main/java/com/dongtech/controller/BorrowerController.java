package com.dongtech.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dongtech.constants.Constants;
import com.dongtech.model.loan.BorrowerInfo;
import com.dongtech.service.loan.BorrowerServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class BorrowerController {
	
	@Autowired
	private BorrowerServiceConsumer borrowerService;
	
	@RequestMapping(value="/loan/borrower")
	public String borrower (Model model, @RequestParam(value="curPage", required=false) Integer currentPage) {
		if (null == currentPage) {
			currentPage = 1;//当前页从1开始
		}
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("currentPage", (currentPage-1)*Constants.PAGESIZE);
		paramMap.put("pageSize", Constants.PAGESIZE);
		List<BorrowerInfo> loanInfoList = borrowerService.getBorrowerByPage(paramMap);
		
		//计算有多少页
		int totalRows = borrowerService.getBorrowerInfoByTotal();
		int totalPage = totalRows / Constants.PAGESIZE;
		int mod = totalRows % Constants.PAGESIZE;
		if (mod > 0) {
			totalPage = totalPage + 1;
		}
		model.addAttribute("loanInfoList", loanInfoList);
		model.addAttribute("totalPage", totalPage);//总页数
		model.addAttribute("currentPage", currentPage);//当前页
		return "borrower";
	}
}
