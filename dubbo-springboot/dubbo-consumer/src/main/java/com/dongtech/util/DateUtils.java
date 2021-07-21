package com.dongtech.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具处理类
 * 
 * @author 东宝
 *
 */
public class DateUtils {

	public final static String FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	
	/**
	 * 获取当前日期的格式化信息
	 * 
	 * @return
	 */
	public static String getCurrentDateByFormat () {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYYMMDDHHMMSSSSS);
		return format.format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(getCurrentDateByFormat());
	}
}
