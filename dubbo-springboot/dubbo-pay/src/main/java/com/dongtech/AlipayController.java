package com.dongtech;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongtech.config.AlipayConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 支付宝充值
 * 
 * @author 东宝
 */
@Controller
public class AlipayController {
	
	private static final Logger logger = LogManager.getLogger(AlipayController.class);

	/**
	 * 去支付宝支付
	 * 
	 * @param request
	 * @param model
	 * @param outTradeNo
	 * @param totalAmount
	 * @param subject
	 * @param body
	 * @param uid
	 * @param returnUrl
	 * @param notifyUrl
	 * @return
	 */
	@RequestMapping("/api/alipay")
	public String alipay (HttpServletRequest request, Model model,
			@RequestParam(value="outTradeNo", required=true) String outTradeNo,//商户订单号，商户网站订单系统中唯一订单号，必填
			@RequestParam(value="totalAmount", required=false) String totalAmount,//付款金额，必填
			@RequestParam(value="subject", required=true) String subject,//订单名称，必填
			@RequestParam(value="body", required=false) String body,//商品描述，可空
			@RequestParam(value="uid", required=false) String uid,
			@RequestParam(value="returnUrl", required=false) String returnUrl, //回调返回的url，是前端业务方传来的url，到时候pay项目需要回调该url地址
			@RequestParam(value="notifyUrl", required=false) String notifyUrl) { //异步通知的url，是前端业务方传来的url，到时候pay项目需要异步通知该url地址
		
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(
				AlipayConfig.GATEWAYURL, 
				AlipayConfig.APP_ID,
				AlipayConfig.MERCHANT_PRIVATE_KEY, 
				AlipayConfig.JSON, 
				AlipayConfig.CHARSET, 
				AlipayConfig.ALIPAY_PUBLIC_KEY, 
				AlipayConfig.SIGN_TYPE);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.RETURN_URL);//回调pay项目，由pay项目回调前端业务方
		alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);//通知pay项目，由pay项目通知前端业务方
		
		try {
			//业务回传参数组装，只能在异步通知返回时候才原样返回
			Map<String, Object> map = new ConcurrentHashMap<String, Object>();
			map.put("uid", uid);
			map.put("returnUrl", returnUrl);
			map.put("notifyUrl", notifyUrl);
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(map);
			String passback_params = java.net.URLEncoder.encode(json, "UTF-8");
			
			logger.debug(passback_params);
			
			//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节的文档
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\"," 
					+ "\"total_amount\":\""+ totalAmount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"body\":\""+ body +"\"," 
					+ "\"passback_params\":\"" + passback_params + "\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			
			//请求支付宝网关
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			logger.info("请求支付宝网关返回结果：" + result);
			
			model.addAttribute("result", result);
			//跳转到页面，由页面执行跳转
			return "toAlipay";
			//return "alipay";
			
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 支付回调，同步返回
	 * 
	 * @param request
	 * @param model
	 * @param notifyUrl
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws AlipayApiException 
	 */
	@RequestMapping(value="/api/alipayBack")
	public String alipayBack (HttpServletRequest request, Model model) {
		
		//获取支付宝回调同步返回的信息
		Map<String, String> params = new HashMap<String, String>();
		
		Map<String,String[]> requestParams = request.getParameterMap();
		logger.info(requestParams);
		
		try {
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i=0; i<values.length; i++) {
					valueStr = (i==values.length-1) ? valueStr+values[i] : valueStr+values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			//调用SDK验证签名
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);

			//验证签名通过，执行自己的业务处理
			if (signVerified) {
				//商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
				//支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
				//付款金额
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
				
				logger.info("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
				
				params.put("isSignOK", "yes");//表示验证签名通过
				model.addAttribute("paramMap", params);
			} else {
				logger.info("验签失败");
				
				params.put("isSignOK", "no");//表示验证签名通过
				model.addAttribute("paramMap", params);
			}
			
			//将回调地址放入model中 http://localhost:8080/p2p/loan/alipayBack
			//String p2p_return_url = ResourceBundle.getBundle("config").getString("p2p_return_url");
			String p2p_return_url = "http://localhost:8081/loan/alipayBack";
			model.addAttribute("p2p_return_url", p2p_return_url);
			
			//通过页面跳转到商户页面
			return "toP2P";
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 支付异步通知
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/api/alipayNotify")
	public String alipayNotify (HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		
		try {
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i=0; i<values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE); //调用SDK验证签名

			//如下这里编写处理逻辑
			/* 实际验证过程建议商户务必添加以下校验：
				1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
				2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
				3、验证app_id是否为该商户本身。
			*/
			if (signVerified) {//验证成功
				//商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
				//支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
				//交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
				if (trade_status.equals("TRADE_FINISHED")) {
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
						
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
				}
				response.getWriter().write("success");
			} else {//验证失败
				response.getWriter().write("fail");
			}
			//以上编写处理逻辑
			
			return "notifyP2P";
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 交易查询接口
	 * 
	 * @param request
	 * @param model
	 * @param outTradeNo
	 * @param tradeNo
	 * @return
	 */
	@RequestMapping(value="/api/alipayQuery")
	public @ResponseBody String alipayQuery (HttpServletRequest request, Model model, 
			@RequestParam(value="outTradeNo", required=true) String outTradeNo//商户订单号，商户网站订单系统中唯一订单号，必填
			) {
		
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(
				AlipayConfig.GATEWAYURL, 
				AlipayConfig.APP_ID, 
				AlipayConfig.MERCHANT_PRIVATE_KEY, 
				AlipayConfig.JSON, 
				AlipayConfig.CHARSET, 
				AlipayConfig.ALIPAY_PUBLIC_KEY, 
				AlipayConfig.SIGN_TYPE);
		
		//设置请求参数
		AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
		
		try {
			//商户订单号、支付宝订单号二选一设置即可
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\"}");
			//请求
			String result = alipayClient.execute(alipayRequest).getBody();
			//日志记录
			logger.info(result);
			
			//调用SDK验证签名
			Map<String, String> params = new HashMap<String, String>();
			params.put("out_trade_no", outTradeNo);
			
			return result;
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
}
