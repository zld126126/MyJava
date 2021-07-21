package com.dongtech.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理方法
 * 
 * @author 东宝
 *
 */
public class DateUtils {

	/**
	 * 当前时间加指定月之后的时间
	 * 
	 * @param months
	 * @return
	 */
	public static Date getCurrentDateAddMonths (int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	
	/**
	 * 指定时间加上指定月份之后的时间
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getDateAddMonths (Date date, int months) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); 
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
}
