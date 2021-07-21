package com.dongtech.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient处理工具类
 * 
 * @author 东宝
 * 
 */
public class HttpClientUtils {

	/**
	 * Get请求
	 * 
	 * @param url 请求URL
	 * @return
	 */
	public static String doGet(String url) {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String result = "";
		try {
			httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(35000)
					.setConnectionRequestTimeout(35000)  
			        .setSocketTimeout(60000)
			        .build();  
			httpGet.setConfig(requestConfig);
			response = httpclient.execute(httpGet);
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
		    	if (null != response) {
		    		response.close();
		    	}
		    	if (null != httpclient) {
		    		httpclient.close();
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 带参数的Post请求
	 * 
	 * @param url 请求URL
	 * @param paramMap 请求参数
	 * @return
	 */
	public static String doPost(String url, Map<String, Object> paramMap) {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String result = "";
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(35000)
					.setConnectionRequestTimeout(35000)  
			        .setSocketTimeout(60000)
			        .build();  
			httpPost.setConfig(requestConfig);
			if (null != paramMap && paramMap.size()>0) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Set<Map.Entry<String, Object>> set = paramMap.entrySet();
				Iterator<Map.Entry<String, Object>> it = set.iterator();
				while (it.hasNext()) {
					Map.Entry<String, Object> mapEntry = it.next();
					nvps.add(new BasicNameValuePair(mapEntry.getKey(), new String(mapEntry.getValue().toString().getBytes("UTF-8"),"iso8859-1")));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			}
			response = httpclient.execute(httpPost);
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
		    	if (null != response) {
		    		response.close();
		    	}
		    	if (null != httpclient) {
		    		httpclient.close();
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 无参数的Post请求
	 * 
	 * @param url 请求URL
	 * @return
	 */
	public static String doPost(String url) {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String result = "";
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(35000)
					.setConnectionRequestTimeout(35000)  
			        .setSocketTimeout(60000)
			        .build();  
			httpPost.setConfig(requestConfig);
			response = httpclient.execute(httpPost);
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
		    	if (null != response) {
		    		response.close();
		    	}
		    	if (null != httpclient) {
		    		httpclient.close();
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 带参数并指定编码的Post请求
	 * 
	 * @param url 请求URL
	 * @param paramMap 请求参数
	 * @param encode 字符编码格式
	 * @return
	 */
	public static String doPostByEncode(String url, Map<String, Object> paramMap, String encode) {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String result = "";
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(35000)
					.setConnectionRequestTimeout(35000)  
			        .setSocketTimeout(60000)
			        .build();  
			httpPost.setConfig(requestConfig);
			if (null != paramMap && paramMap.size()>0) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Set<Map.Entry<String, Object>> set = paramMap.entrySet();
				Iterator<Map.Entry<String, Object>> it = set.iterator();
				while (it.hasNext()) {
					Map.Entry<String, Object> mapEntry = it.next();
					nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, encode));
			}
			response = httpclient.execute(httpPost);
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
		    	if (null != response) {
		    		response.close();
		    	}
		    	if (null != httpclient) {
		    		httpclient.close();
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}