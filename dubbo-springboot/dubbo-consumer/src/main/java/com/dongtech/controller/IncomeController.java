package com.dongtech.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import com.dongtech.constants.Constants;
import com.dongtech.model.loan.IncomeRecord;
import com.dongtech.model.user.User;
import com.dongtech.service.loan.IncomeRecordServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 收益计划相关处理Controller
 * 
 * @author 东宝
 *
 */
@Controller
public class IncomeController {
	
	@Autowired
	private IncomeRecordServiceConsumer incomeRecordService;

	/**
	 * 我的收益记录列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loan/myIncome")
	public String myIncome (HttpServletRequest request, Model model,
			@RequestParam(value="currentPage", required=false) Integer currentPage) {
		
		if (null == currentPage) {
			currentPage = 1;//当前页从1开始
		}
		
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		
		//获取用户投资记录
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("uid", user.getId());
		paramMap.put("currentPage", (currentPage-1)*Constants.PAGESIZE);
		paramMap.put("pageSize", Constants.PAGESIZE);
		
		List<IncomeRecord> incomeRecordList = incomeRecordService.getIncomeRecordByPage(paramMap);
		
		//符合查询条件的数据总条数
		int totalRows = incomeRecordService.getIncomeRecordByTotal(paramMap);
		
		//计算有多少页
		int totalPage = totalRows / Constants.PAGESIZE;
		int mod = totalRows % Constants.PAGESIZE;
		if (mod > 0) {
			totalPage = totalPage + 1;
		}
		
		model.addAttribute("incomeRecordList", incomeRecordList);
		model.addAttribute("totalPage", totalPage);//总页数
		model.addAttribute("currentPage", currentPage);//当前页
		model.addAttribute("totalRows", totalRows);//总数据条数
				
		return "myIncome";
	}
}
