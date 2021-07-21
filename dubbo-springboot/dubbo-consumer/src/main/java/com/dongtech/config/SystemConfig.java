package com.dongtech.config;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件处理类
 * 
 * @author 东宝
 * 
 */
public class SystemConfig {
	
	private static final Logger logger = LogManager.getLogger(SystemConfig.class);
	
	private static Properties configProperties = new Properties();
	
	static {
		InputStream is = null;
		try {
			is = SystemConfig.class.getResourceAsStream("/config.properties");
			configProperties.load(is);
			logger.info("加载配置文件config.properties完成......");
		} catch (IOException e) {
			logger.info("加载properties配置文件发生异常，请重启服务再次尝试......");
			e.printStackTrace();
		} finally {
			try {
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {
				logger.info("加载properties配置文件发生异常，请重启服务再次尝试......");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取config文件配置参数
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfigProperty(String key){
		return configProperties.getProperty(key);
	}

	/**
	 * 根据配置文件名和key获取配置参数
	 * 
	 * @param key
	 * @param filename
	 * @return
	 */
	public static String getValueByPropertiesFile(String key, String filename) {
		Properties props = new Properties();
		try {
			String pathString = SystemConfig.class.getResource("/").getPath();
			if ("/".equals(File.separator)) {   
				pathString = pathString.replace("\\",File.separator);
			}
			pathString = pathString + filename;
			pathString =  pathString.replace("%20"," ");
			FileInputStream inputStream=new FileInputStream(pathString);
			props.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = props.getProperty(key);
		if (value == null) {
			return "";
		}
		return value;
	}
}
