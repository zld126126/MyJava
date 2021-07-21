package com.demo.blog;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.render.RedirectRender;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * BlogInterceptor
 * 此拦截器仅做为示例展示，在本 demo 中并不需要
 */
public class BlogInterceptor implements Interceptor {
	
	public void intercept(Invocation inv) {
		//获取登陆controller
		Controller controller = inv.getController();
		// 获取当前url
		String actionkey = inv.getActionKey();
		System.out.println("Before invoking " + inv.getActionKey());
		if ("/blog".equals(actionkey)||actionkey.startsWith("/blog/")) {
			inv.invoke();
		}else{
			controller.setAttr("statusCode", 200);
            controller.setAttr("message", "用户无权操作");

            // 用户没有登录，则弹出登录确认框
            controller.setAttr("show_pop_login", "show_pop_login");
            controller.renderJson();
		}
		System.out.println("After invoking " + inv.getActionKey());
	}
}
