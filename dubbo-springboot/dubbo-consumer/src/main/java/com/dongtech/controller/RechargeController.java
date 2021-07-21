package com.dongtech.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dongtech.config.SystemConfig;
import com.dongtech.constants.Constants;
import com.dongtech.model.loan.RechargeRecord;
import com.dongtech.model.user.User;
import com.dongtech.service.loan.OnlyNumberServiceConsumer;
import com.dongtech.service.loan.RechargeServiceConsumer;
import com.dongtech.service.user.UserService;
import com.dongtech.service.user.UserServiceConsumer;
import com.dongtech.util.DateUtils;
import com.dongtech.util.HttpClientUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 充值相关处理Controller
 * 
 * @author 东宝
 *
 */
@Controller
public class RechargeController {

	private static final Logger logger = LogManager.getLogger(RechargeController.class);

	@Autowired
	private RechargeServiceConsumer rechargeService;

	@Autowired
	private UserServiceConsumer userService;
	
	@Autowired
	private OnlyNumberServiceConsumer onlyNumberService;

	/**
	 * 去充值，跳转至充值页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loan/intoRecharge")
	public String intoRecharge (HttpServletRequest request, Model model) {
		//处理自己的业务逻辑
		logger.info("去充值，进入充值页面......");
		return "toRecharge";
	}

	/**
	 * 去充值
	 * 
	 * @param request
	 * @param model
	 * @param rechargeMoney
	 * @return
	 */
	@RequestMapping(value="/loan/toRecharge")
	public String toRecharge (HttpServletRequest request, Model model,
			@RequestParam(value="rechargeMoney", required=true) Double rechargeMoney) {//充值金额
		
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		
		//获取唯一数字
		Long onlyNumber = onlyNumberService.getOnlyNumber();
		//获取当前格式化的日期串
		String currentDateByFormat = DateUtils.getCurrentDateByFormat();
		
		//先下单，再充值
		String rechargeNo = currentDateByFormat + onlyNumber;//订单编号
		RechargeRecord rechargeRecord = new RechargeRecord();
		rechargeRecord.setRechargeMoney(rechargeMoney);//充值金额
		rechargeRecord.setRechargeTime(new Date());//充值时间
		rechargeRecord.setRechargeNo(rechargeNo);//订单号要求全站唯一
		rechargeRecord.setRechargeDesc("支付理财");
		rechargeRecord.setUid(user.getId());//用户ID
		rechargeRecord.setRechargeStatus("0");//0表示充值中，1充值成功
		
		//下单
		int addRow = rechargeService.addRechargeRecord(rechargeRecord);
		if (addRow <= 0) {
			//下单失败，返回给页面提示信息
			model.addAttribute("errorMessage", "服务太忙，请稍后再试……");
			return "toRecharge";
		}
		
		//从配置文件获取配置的支付url、回调url、通知url
		String alipay_pay_url = SystemConfig.getConfigProperty("alipay_pay_url");
		String alipay_return_url = SystemConfig.getConfigProperty("alipay_return_url");
		String alipay_notify_url = SystemConfig.getConfigProperty("alipay_notify_url");
		
		model.addAttribute("rechargeMoney", rechargeMoney);
		model.addAttribute("rechargeNo", rechargeNo);
		
		//支付宝支付的url
		model.addAttribute("alipay_pay_url", alipay_pay_url);
		//返回到p2p的url
		model.addAttribute("alipay_return_url", alipay_return_url);
		//通知到p2p的url
		model.addAttribute("alipay_notify_url", alipay_notify_url);
		//跳转至一个页面，页面提交至alipay
		return "toAlipay";
	}
	
	/**
	 * 支付宝充值返回
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loan/alipayBack")
	public String alipayBack (HttpServletRequest request, Model model) {
		logger.info("支付宝回调返回......");
		//处理自己的业务逻辑
		String rechargeNo = request.getParameter("out_trade_no");
		String rechargeMoney = request.getParameter("total_amount");
		String isSignOK = request.getParameter("isSignOK");
		
		if ("yes".equals(isSignOK)) {//签名通过
			
			//我们需要查询一下支付结果，如果支付成功就更新订单状态，不成功，则不更新订单状态
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("outTradeNo", rechargeNo);
			
			String url = SystemConfig.getConfigProperty("alipay_query_url");
			String json = HttpClientUtils.doPost(url, paramMap);
			
			//解析json
			JSONObject jsonObject = JSON.parseObject(json);

			JSONObject jObject = jsonObject.getJSONObject("alipay_trade_query_response");
			String code = jObject.getString("code");
			if ("10000".equals(code)) {//调用成功
				String tradeStatus = jObject.getString("trade_status");
				if ("TRADE_SUCCESS".equals(tradeStatus)) {
					//交易成功
					//将充值订单状态改为成功，用户可用余额增加
					int recharge = rechargeService.recharge(rechargeNo, Double.parseDouble(rechargeMoney), 1);
					logger.info("充值结果:" +  recharge);
				}
			}
			//跳转到充值记录页面
			//return "redirect:../loan/myRecharge";
			User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
			if(null==user){
				Integer uid=userService.getUserIdByRechargeRecordNo(rechargeNo);
				user = userService.getUserById(uid);
			}
			request.getSession().setAttribute(Constants.SESSION_USER, user);
			return myRecharge(request,model,1);
		} else {
			//跳转到一个页面，提示交易异常信息
			model.addAttribute("trade_msg", "签名验证失败");
			return "toRechargeBack";
		}
	}
	
	/**
	 * 支付宝充值后台通知
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loan/alipayNotify")
	public String alipayNotify (HttpServletRequest request, Model model) {
		//处理自己的业务逻辑
		logger.info("支付宝异步通知返回......");
		return "myRecharge";
	}
	
	/**
	 * 我的充值记录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loan/myRecharge")
	public String myRecharge (HttpServletRequest request, Model model,
			@RequestParam(value="currentPage", required=false) Integer currentPage) {
		
		if (null == currentPage) {
			currentPage = 1;//当前页从1开始
		}

		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("uid", user.getId());
		paramMap.put("currentPage", (currentPage-1)*Constants.PAGESIZE);
		paramMap.put("pageSize", Constants.PAGESIZE);
		
		int totalRows = rechargeService.getRechargeRecordByCount(user.getId());
		
		List<RechargeRecord> rechargeRecordList = rechargeService.getRechargeRecordByPage(paramMap);
		
		//计算有多少页
		int totalPage = totalRows / Constants.PAGESIZE;
		int mod = totalRows % Constants.PAGESIZE;
		if (mod > 0) {
			totalPage = totalPage + 1;
		}
		
		model.addAttribute("totalRows", totalRows);//总数据条数
		model.addAttribute("totalPage", totalPage);//总页数
		model.addAttribute("currentPage", currentPage);//当前页
		model.addAttribute("rechargeRecordList", rechargeRecordList);//充值记录数据
				
		return "myRecharge";
	}
}
