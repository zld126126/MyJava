package com.dongtech.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.dongtech.config.SystemConfig;
import com.dongtech.constants.Constants;
import com.dongtech.model.loan.BidInfo;
import com.dongtech.model.loan.IncomeRecord;
import com.dongtech.model.loan.RechargeRecord;
import com.dongtech.model.user.FinanceAccount;
import com.dongtech.model.user.User;
import com.dongtech.service.loan.*;
import com.dongtech.service.user.FinanceAccountServiceConsumer;
import com.dongtech.service.user.UserServiceConsumer;
import com.dongtech.upload.FileUpload;
import com.dongtech.util.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;


/**
 * 用户相关操作Controller
 * 
 * @author 东宝
 *
 */
@Controller
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserServiceConsumer userService;
	
	@Autowired
	private FinanceAccountServiceConsumer financeAccountService;
	
	@Autowired
	private BidInfoServiceConsumer bidInfoService;

	@Autowired
	private IncomeRecordServiceConsumer incomeRecordService;
	
	@Autowired
	private RechargeServiceConsumer rechargeService;
	
	/**
	 * 用户注册
	 * 
	 * @param phone
	 * @param loginPassword
	 * @param replayLoginPassword
	 * @param captcha
	 * @return
	 */
	@RequestMapping(value="/loan/register", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> register (HttpServletRequest request, Model model, 
			@RequestParam (value="phone", required=true) String phone,//手机号
			@RequestParam (value="loginPassword", required=true) String loginPassword,//登录密码
			@RequestParam (value="replayLoginPassword", required=true) String replayLoginPassword,//确认登录密码
			@RequestParam (value="captcha", required=true) String captcha) {//图形验证码
		
		Map<String, Object> retMap = new ConcurrentHashMap<String, Object>();
		
		//服务端对参数再次进行验证
		if (StringUtils.isEmpty(phone)) {
			retMap.put("errorMessage", "请输入注册手机号");
			return retMap;
		}
		//正则验证手机号
		if (!Pattern.matches("^1[1-9]\\d{9}$", phone)) {
			retMap.put("errorMessage", "请输入正确的手机号");
			return retMap;
		}
		if (StringUtils.isEmpty(loginPassword)) {
			retMap.put("errorMessage", "请输入登录密码");
			return retMap;
		}
		if (StringUtils.isEmpty(replayLoginPassword)) {
			retMap.put("errorMessage", "请输入确认密码");
			return retMap;
		}
		if (!StringUtils.equals(loginPassword, replayLoginPassword)) {
			retMap.put("errorMessage", "两次输入的密码不一致");
			return retMap;
		}
		//如果验证码不为空，验证一下是否与session中的验证码一致
		if (StringUtils.isNotEmpty(captcha)) {
			String sessionCaptcha = (String)request.getSession().getAttribute(Constants.CAPTCHA);
			if (!StringUtils.equalsIgnoreCase(sessionCaptcha, captcha)) {
				retMap.put("errorMessage", "图形验证码不正确");
				return retMap;
			}
		}
		
		User user = new User();
		user.setPhone(phone);
		user.setLoginPassword(loginPassword);
		user.setAddTime(new Date());//注册时间
		user.setLastLoginTime(new Date());//注册成功当做第一次登录时间
		int loginUser = userService.register(user);
		if (loginUser > 0) {
			retMap.put("errorMessage", "ok");
			//注册完之后让用户登录一下
			User uUser = userService.login(phone, loginPassword);
			//登录后将用户信息放入session中
			request.getSession().setAttribute(Constants.SESSION_USER, uUser);
		} else {
			retMap.put("errorMessage", "注册失败了，请重试");
		}
		return retMap;
	}
	
	/**
	 * 根据手机号查询用户是否存在
	 * 
	 * @param phone
	 * @param loginPassword
	 * @param replayLoginPassword
	 * @param captcha
	 * @return
	 */
	@RequestMapping(value="/loan/phoneCheck", method=RequestMethod.POST)
	public @ResponseBody String phoneCheck (Model model, @RequestParam (value="phone", required=true) String phone) {
		
		//根据手机号去数据库查询数据
		int regUser = userService.getUserByPhone(phone);
		return String.valueOf(regUser);
	}
	
	/**
	 * 用户登录
	 * 
	 * @param phone
	 * @param loginPassword
	 * @return
	 */
	@RequestMapping(value="/loan/login", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> login (HttpServletRequest request, Model model, 
			@RequestParam (value="phone", required=true) String phone,
			@RequestParam (value="loginPassword", required=true) String loginPassword,
			@RequestParam (value="captcha", required=false) String captcha) {
		
		Map<String, Object> retMap = new ConcurrentHashMap<String, Object>();
		
		//服务端对参数再次进行验证
		if (StringUtils.isEmpty(phone)) {
			retMap.put("errorMessage", "请输入登录手机号");
			return retMap;
		}
		//正则验证手机号
		if (!Pattern.matches("^1[1-9]\\d{9}$", phone)) {
			retMap.put("errorMessage", "请输入正确的手机号");
			return retMap;
		}
		if (StringUtils.isEmpty(loginPassword)) {
			retMap.put("errorMessage", "请输入登录密码");
			return retMap;
		}
		//如果验证码不为空，验证一下是否与session中的验证码一致
		if (StringUtils.isNotEmpty(captcha)) {
			String sessionCaptcha = (String)request.getSession().getAttribute(Constants.CAPTCHA);
			if (!StringUtils.equalsIgnoreCase(sessionCaptcha, captcha)) {
				retMap.put("errorMessage", "图形验证码不正确");
				return retMap;
			}
		}
		User uUser = userService.login(phone, loginPassword);
		if (null == uUser) {
			retMap.put("errorMessage", "您输入的手机号或密码不匹配");
			
			return retMap;
		}
		model.addAttribute("loginUser", uUser);
		
		//登录后将用户信息放入session中
		request.getSession().setAttribute(Constants.SESSION_USER, uUser);
		
		retMap.put("errorMessage", "ok");
		
		return retMap;
	}
	
	/**
	 * 验证码检查
	 * 
	 * @param phone
	 * @param loginPassword
	 * @return
	 */
	@RequestMapping(value="/loan/verifyCaptcha", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> verifyCaptcha (HttpServletRequest request, Model model, 
			@RequestParam (value="captcha", required=true) String captcha) {
		
		Map<String, Object> retMap = new ConcurrentHashMap<String, Object>();
		
		//如果验证码不为空，验证一下是否与session中的验证码一致
		if (StringUtils.isNotEmpty(captcha)) {
			String sessionCaptcha = (String)request.getSession().getAttribute(Constants.CAPTCHA);
			if (!StringUtils.equalsIgnoreCase(sessionCaptcha, captcha)) {
				retMap.put("errorMessage", "图形验证码不正确");
				return retMap;
			}
		}
		retMap.put("errorMessage", "ok");
		return retMap;
	}
	
	/**
	 * 用户个人中心(个人小金库)
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loan/myCenter")
	public String myCenter (HttpServletRequest request, Model model) {
		
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		
		//获取用户财务资金信息
		FinanceAccount financeAccount = financeAccountService.getFinanceAccountByUid(user.getId().longValue());
		model.addAttribute(Constants.SESSION_FINANCEACCOUNT, financeAccount);
		
		//获取用户最近投资记录
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("uid", user.getId());
		paramMap.put("currentPage", 0);
		paramMap.put("pageSize", 5);
		List<BidInfo> bidInfoList = bidInfoService.getBidInfoByPage(paramMap);
		model.addAttribute("bidInfoList", bidInfoList);
		
		//获取用户最近充值记录(1充值、2投资)
		List<RechargeRecord> rechargeRecordList = rechargeService.getRechargeRecordByPage(paramMap);
		model.addAttribute("rechargeRecordList", rechargeRecordList);
		
		//获取用户最近收益记录
		List<IncomeRecord> incomeRecordList = incomeRecordService.getIncomeRecordByPage(paramMap);
		model.addAttribute("incomeRecordList", incomeRecordList);
		
		return "myCenter";
	}
	
	/**
	 * 用户退出登录
	 * 
	 * @param phone
	 * @param loginPassword
	 * @return
	 */
	@RequestMapping(value="/loan/logout")
	public String logout (HttpServletRequest request, Model model) {
		
		request.getSession().removeAttribute(Constants.SESSION_USER);
		request.getSession().removeAttribute(Constants.SESSION_FINANCEACCOUNT);
		return "redirect:/index";
	}
	
	/**
	 * 上传头像
	 * 
	 * @param request
	 * @param uid
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/loan/uploadHeader", method=RequestMethod.POST)
	public Map<String, Object> uploadimg( HttpServletRequest request,
			@RequestParam (value="uid", required=true) Integer uid,
			@RequestParam (value="fieldName", required=false) MultipartFile file) {

		Map<String, Object> retMap = new ConcurrentHashMap<String, Object>();
		
		String filename = FileUpload.writeUploadFile(file);
		if (filename.equals("NOT_IMAGE")) {
			retMap.put("errorMessage", "您上传的不是图片文件");
			return retMap;
		}
		
		User user = new User();
		user.setId(uid);
		user.setHeaderImage(filename);//头像路径
		int updateHeader = userService.updateUserById(user);
		
		return retMap;
	}
	
	/**
	 * 我的账户
	 * 
	 * @param phone
	 * @param loginPassword
	 * @return
	 */
	@RequestMapping(value="/loan/myAccount")
	public String myAccount (HttpServletRequest request, Model model) {
		
		return "myAccount";
	}
	
	/**
	 * 实名认证
	 * 
	 * @param request
	 * @param model
	 * @param realName
	 * @param idCard
	 * @param replayIdCard
	 * @param captcha
	 * @return
	 * @throws IOException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value="/loan/realName")
	public @ResponseBody Map<String, Object> realName (HttpServletRequest request, Model model, 
			@RequestParam (value="realName", required=true) String realName,
			@RequestParam (value="idCard", required=true) String idCard,
			@RequestParam (value="replayIdCard", required=true) String replayIdCard,
			@RequestParam (value="captcha", required=true) String captcha) {
		
		Map<String, Object> retMap = new ConcurrentHashMap<String, Object>();
		
		//服务端对参数再次进行验证
		if (StringUtils.isEmpty(realName)) {
			retMap.put("errorMessage", "请输入真实姓名");
			return retMap;
		}
		String reg = "[\\u4e00-\\u9fa5]+";//+表示一个或多个中文，
		if (!realName.matches(reg)) {
			retMap.put("errorMessage", "请输入真实的中文汉字姓名");
			return retMap;
		}
		if (StringUtils.isEmpty(idCard)) {
			retMap.put("errorMessage", "请输入身份证号码");
			return retMap;
		}
		if (StringUtils.isEmpty(replayIdCard)) {
			retMap.put("errorMessage", "请输入确认身份证号码");
			return retMap;
		}
		if (!StringUtils.equals(idCard, replayIdCard)) {
			retMap.put("errorMessage", "两次输入的身份证号码不一致");
			return retMap;
		}
		//如果验证码不为空，验证一下是否与session中的验证码一致
		if (StringUtils.isEmpty(captcha)) {
			retMap.put("errorMessage", "请输入图形验证码");
			return retMap;
		} else {
			String sessionCaptcha = (String)request.getSession().getAttribute(Constants.CAPTCHA);
			if (!StringUtils.equalsIgnoreCase(sessionCaptcha, captcha)) {
				retMap.put("errorMessage", "图形验证码不正确");
				return retMap;
			}
		}
		
		//先去检查一下用户身份证号码是否在数据库中已存在，存在则不能进行实名认证
		int isExistNum = userService.getUserByIdCard(idCard);
		if (isExistNum > 0) {
			retMap.put("errorMessage", "该身份证号码已被使用");
			return retMap;
		}
		
		//调用第三方的实名认证接口进行实名认证，实名认证通过后则进行下面的业务处理
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("realName", realName);
		paramMap.put("idCard", idCard);
		paramMap.put("apiId", SystemConfig.getConfigProperty("apiId"));
		paramMap.put("apiKey", SystemConfig.getConfigProperty("apiKey"));
		
		String json = HttpClientUtils.doPostByEncode(SystemConfig.getConfigProperty("realname_url"), paramMap, Constants.UTF8);
		
		logger.info(json);//sf-json, jackson, fastjson, Gson
		
		//解析json数据
		String errorCode = "";
		String errorMessage = "";
		try {
			JsonFactory jasonFactory = new JsonFactory();
			JsonParser jsonParser = jasonFactory.createParser(json);
			while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
				//get the current token
				String fieldName = jsonParser.getCurrentName();
				if ("errorCode".equals(fieldName)) {
					//move to next token
					jsonParser.nextToken();
					errorCode = jsonParser.getText();
			      	System.out.println(errorCode);        	 
				}
				if ("errorMessage".equals(fieldName)) {
					//move to next token
					jsonParser.nextToken();
					errorMessage = jsonParser.getText();
			      	System.out.println(errorCode);        	 
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if ("0".equals(errorCode)) {
			User sessionUser = (User)request.getSession().getAttribute(Constants.SESSION_USER);
			
			User user = new User();
			user.setName(realName);
			user.setIdCard(idCard);
			user.setId(sessionUser.getId());
			int loginUser = userService.updateUserById(user);
			if (loginUser > 0) {
				retMap.put("errorMessage", "ok");
				//实名认证成功之后，从数据库获取一遍用户信息，更新sessioon中存储的用户信息
				User newUser = userService.getUserById(sessionUser.getId());
				request.getSession().setAttribute(Constants.SESSION_USER, newUser);
			} else {
				retMap.put("errorMessage", "实名认证失败了，请重试");
			}
			return retMap;
		} else {
			retMap.put("errorMessage", errorMessage==null ? "实名认证失败了，请重试" : errorMessage);
		}
		return retMap;
	}
}
