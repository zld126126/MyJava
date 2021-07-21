package com.dongtech.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import com.dongtech.constants.Constants;
import com.dongtech.model.common.ReturnObject;
import com.dongtech.model.loan.BidInfo;
import com.dongtech.model.loan.LoanInfo;
import com.dongtech.model.user.FinanceAccount;
import com.dongtech.model.user.User;
import com.dongtech.service.loan.BidInfoServiceConsumer;
import com.dongtech.service.loan.LoanInfoServiceConsumer;
import com.dongtech.service.user.FinanceAccountServiceConsumer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 投资相关处理Controller
 * 
 * @author 东宝
 *
 */
@Controller
public class BidController {

	private static final Logger logger = LogManager.getLogger(BidController.class);

	@Autowired
	private FinanceAccountServiceConsumer financeAccountService;
	
	@Autowired
	private BidInfoServiceConsumer bidInfoService;
	
	@Autowired
	private LoanInfoServiceConsumer loanInfoService;
	
	/**
	 * 投资
	 * 
	 * @param request
	 * @param model
	 * @param attr
	 * @param bidMoney
	 * @param loanId
	 * @return
	 */
	@RequestMapping(value="/loan/invest", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> invest (HttpServletRequest request, Model model, RedirectAttributes attr,
			@RequestParam (value="bidMoney", required=true) Double bidMoney,
			@RequestParam (value="loanId", required=true) Integer loanId) {
		
		Map<String, Object> retMap = new ConcurrentHashMap<String, Object>();
		
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		
		//验证用户余额是否充足
		FinanceAccount financeAccount = financeAccountService.getFinanceAccountByUid(user.getId().longValue());
		if (null == financeAccount || financeAccount.getAvailableMoney() <= 0 || financeAccount.getAvailableMoney() < bidMoney) {
			logger.info("投标余额不足，uid=" + user.getId());
			//余额不足，请先充值
			retMap.put("errorMessage", "余额不足，请先充值");//余额不足，请先充值
			return retMap;
		} else {
			//获取产品信息
			LoanInfo loanInfo = loanInfoService.getLoanInfoById(loanId);
			
			if (bidMoney < loanInfo.getBidMinLimit()) {
				//起投投资金额不能低于bidMinLimit元
				retMap.put("errorMessage", "起投投资金额不能低于"+loanInfo.getBidMinLimit()+"元");
				return retMap;
			}
			if (bidMoney > loanInfo.getBidMaxLimit()) {
				//单笔投资金额不能超过bidMaxLimit元
				retMap.put("errorMessage", "单笔投资金额不能超过"+loanInfo.getBidMaxLimit()+"元");
				return retMap;
			}
			
			//调用底层的dubbo服务完成投资
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bidMoney", bidMoney);
			paramMap.put("loanId", loanId);
			paramMap.put("uid", user.getId());
			paramMap.put("phone", user.getPhone());
			
			logger.info("去投资，投资参数" + paramMap);
			ReturnObject returnObject = bidInfoService.addBidInfo(paramMap);
			if ("0".equals(returnObject.getErrorCode())) {//投资成功
				//弹出投资成功提示，确定关闭后，进入投资记录页面
				retMap.put("errorMessage", "ok");//投资成功
				return retMap;
			} else {
				//弹出投资失败提示，确定关闭后，也进入投资记录页面
				retMap.put("errorMessage", "哎呀，投资失败了，请过会儿再来投资吧~");//投资失败
				return retMap;
			}
		}
	}
	
	/**
	 * 我的投资记录列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loan/myInvest")
	public String myInvest (HttpServletRequest request, Model model,
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
		
		List<BidInfo> bidInfoList = bidInfoService.getBidInfoByPage(paramMap);
		
		//符合查询条件的数据总条数
		int totalRows = bidInfoService.getBidInfoByTotal(paramMap);
		
		//计算有多少页
		int totalPage = totalRows / Constants.PAGESIZE;
		int mod = totalRows % Constants.PAGESIZE;
		if (mod > 0) {
			totalPage = totalPage + 1;
		}
		
		model.addAttribute("bidInfoList", bidInfoList);
		model.addAttribute("totalPage", totalPage);//总页数
		model.addAttribute("currentPage", currentPage);//当前页
		model.addAttribute("totalRows", totalRows);//总数据条数
				
		return "myInvest";
	}
}
