package com.dongtech.constants;

import com.dongtech.config.SystemConfig;

/**
 * 常量类
 */
public class Constants {

	/**验证码全局统一命名为captcha*/
	public static final String CAPTCHA = "captcha";
	
	/**产品列表页每页展示产品数*/
	public static final int PAGESIZE = 9;
	
	/**字符编码*/
	public static final String UTF8 = "utf-8";
	
	/**session中存放的user信息*/
	public static final String SESSION_USER = "user";
	
	/**session中存放的用户资金财务信息*/
	public static final String SESSION_FINANCEACCOUNT = "financeAccount";
	
	/**获取操作系统类型*/
	public static final String OS_NAME = System.getProperty("os.name").toLowerCase();
	
	/**获取配置文件路径*/
	public static final String FILE_ROOT = OS_NAME.startsWith("win") ? SystemConfig.getConfigProperty("win_root") : SystemConfig.getConfigProperty("linux_root");

}
