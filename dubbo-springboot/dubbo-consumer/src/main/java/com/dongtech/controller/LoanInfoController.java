package com.dongtech.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import com.dongtech.constants.Constants;
import com.dongtech.model.loan.BidInfo;
import com.dongtech.model.loan.LoanInfo;
import com.dongtech.model.user.FinanceAccount;
import com.dongtech.model.user.TopUser;
import com.dongtech.model.user.User;
import com.dongtech.service.loan.BidInfoServiceConsumer;
import com.dongtech.service.loan.LoanInfoServiceConsumer;
import com.dongtech.service.user.FinanceAccountServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 产品信息处理Controller
 * 
 * @author 东宝
 *
 */
@Controller
public class LoanInfoController {

	@Autowired
	private LoanInfoServiceConsumer loanInfoService;
	
	@Autowired
	private BidInfoServiceConsumer bidInfoService;
	
	@Autowired
	private FinanceAccountServiceConsumer financeAccountService;
	
	@RequestMapping(value="/loan/loan")
	public String loan (Model model, @RequestParam(value="currentPage", required=false) Integer currentPage,
			@RequestParam(value="ptype", required=false) Integer productType) {
		
		if (null == currentPage) {
			currentPage = 1;//当前页从1开始
		}
		
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("currentPage", (currentPage-1)*Constants.PAGESIZE);
		paramMap.put("pageSize", Constants.PAGESIZE);
		if (null != productType) {
			paramMap.put("productType", productType);
		}
		List<LoanInfo> loanInfoList = loanInfoService.getLoanInfoByPage(paramMap);
		
		//符合查询条件的数据总条数
		int totalRows = loanInfoService.getLoanInfoByTotal(paramMap);
		
		//计算有多少页
		int totalPage = totalRows / Constants.PAGESIZE;
		int mod = totalRows % Constants.PAGESIZE;
		if (mod > 0) {
			totalPage = totalPage + 1;
		}
		
		model.addAttribute("productType", productType);//产品类型
		model.addAttribute("loanInfoList", loanInfoList);
		model.addAttribute("totalPage", totalPage);//总页数
		model.addAttribute("currentPage", currentPage);//当前页
		model.addAttribute("totalRows", totalRows);//总数据条数
		
		//调用底层的dubbo服务获取投资排行榜
		List<TopUser> topUserList = bidInfoService.getBidTop();
		model.addAttribute("topUserList", topUserList);
		
		return "loan";
	}
	
	/**
	 * 根据产品ID获取产品详情信息
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/loan/loanInfo")
	public String loanInfo (HttpServletRequest request, Model model, 
			@RequestParam(value="id", required=true) Integer id) {
		
		//投资后跳转到该方法后传的参数
		LoanInfo loanInfo = loanInfoService.getLoanInfoById(id);
		model.addAttribute("loanInfo", loanInfo);
		
		//获取该产品已投资的记录
		List<BidInfo> bidInfoList = bidInfoService.getBidInfoByLoanId(id);
		model.addAttribute("bidInfoList", bidInfoList);
		
		//获取用户财务资金信息
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		if (null != user) {
			FinanceAccount financeAccount = financeAccountService.getFinanceAccountByUid(user.getId().longValue());
			model.addAttribute(Constants.SESSION_FINANCEACCOUNT, financeAccount);
		}
		
		return "loanInfo";
	}
}