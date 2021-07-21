/**  
 * <p>Title: TestController.java</p>  
 * <p>Description: </p>    
 * @author dongbao  
 * @date 2018年8月13日
*/  
package com.demo.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**  
 * <p>Title: TestController.java</p>  
 * <p>Description: </p>    
 * @author dongbao  
 * @date 2018年8月13日
*/
public class TestController extends Controller{
	/**
	 * 测试 test/test
	 */
	public void test() {
		getHandle();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "200");
		jsonObject.put("data", "");
		jsonObject.put("msg", "网络联通");
		System.out.println(jsonObject);
		renderJson(jsonObject);
	}

	/**
	 * get请求跨域解决
	 */
	private void getHandle() {
		HttpServletResponse response = this.getResponse();
		String Origin = this.getRequest().getHeader("Origin");
		System.out.println(Origin);
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Origin", Origin);
		response.addHeader("Access-Control-Allow-Credentials", "true");
	}
	/**
	 * post请求跨域解决
	 */
	private void handle() {
		HttpServletResponse response = this.getResponse();
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
	}
	
	public void testselect() {
		handle();
		List<Record> find = Db.find("select * from blog");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "1");
		jsonObject.put("data", find);
		jsonObject.put("msg", "成功");
		System.out.println(jsonObject);
		renderJson(jsonObject);
	}
	
	public void jsonp() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		   Map json=new HashMap();
		   json.put("status",0);
		   json.put("data", "成功");
		   String callback = getRequest().getParameter("callback"); 
		   json.put("res",true);
		   String jsonp = callback+"("+JSONObject.toJSONString(json)+")";//返回的json 格式要加callback()
		   renderJson(jsonp);
	}
	/**
	 * vuejs 获取后台数据 get请求
	 */
	public void getCarList() {
		//解决vue跨域问题 get
		getHandle();
		
		//返回数据
		JSONObject jsonObject = new JSONObject();
		
		//死数据
		/*List<Map<String, Object>> carList = new ArrayList<Map<String, Object>>();
		Map<String, Object> car1 = new HashMap<>();
		car1.put("id", 1);
		car1.put("name", "奔驰");
		car1.put("ctime", new Date());
		Map<String, Object> car2 = new HashMap<>();
		car2.put("id", 2);
		car2.put("name", "路虎");
		car2.put("ctime", new Date());
		carList.add(car1);
		carList.add(car2);*/
		//真实数据库数据
		String sql = "select id,name,ctime from carlist";
		List<Record> find = Db.find(sql);
		if(null!=find&&find.size()!=0) {
			jsonObject.put("status", 0);
			jsonObject.put("msg", find);
		}else {
			jsonObject.put("status", 1);
			jsonObject.put("msg", "请求失败");
		}
		System.out.println(jsonObject);
		renderJson(jsonObject);
	}
	/**
	 * 添加汽车 post 
	 * 参数:name
	 */
	public void addCar() {
		handle();
		//返回数据
		JSONObject jsonObject = new JSONObject();
		
	    String name = getPara("name");
	    if(null!=name&&""!=name) {
	    	String sql = "insert into carlist (name,ctime) values (?,?)";
		    int update = Db.update(sql,name,new Date());
		    if (update==1) {
		    	jsonObject.put("status", 0);
				jsonObject.put("msg", "添加成功");
			}else {
				jsonObject.put("status", 1);
				jsonObject.put("msg", "添加失败");
			}
	    }else {
	    	jsonObject.put("status", 1);
			jsonObject.put("msg", "添加名称不能为空");
	    }
	    System.out.println(jsonObject);
		renderJson(jsonObject);
	}
	/**
	 * 删除汽车 
	 * 参数:id
	 */
	public void delCar() {
		//解决vue跨域问题 get
		getHandle();
		
		//返回数据
		JSONObject jsonObject = new JSONObject();
		
		String id = getPara("id");
	    if(null!=id&&""!=id) {
	    	String sql = "delete from carlist where id = ?";
		    int delete = Db.delete(sql,id);
		    if (delete==1) {
		    	jsonObject.put("status", 0);
				jsonObject.put("msg", "删除成功");
			}else {
				jsonObject.put("status", 1);
				jsonObject.put("msg", "删除失败");
			}
	    }else {
	    	jsonObject.put("status", 1);
			jsonObject.put("msg", "id不能为空");
	    }
	    System.out.println(jsonObject);
		renderJson(jsonObject);
	}
}
