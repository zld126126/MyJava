package com.demo.index;

import com.demo.blog.BlogInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.render.RedirectRender;

/**
 * 主页拦截器
 *
 */

public class IndexInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		//获取登陆controller
		Controller controller = inv.getController();
		// 获取当前url
		String actionkey = inv.getActionKey();
		
		System.out.println("Before invoking " + inv.getActionKey());
		if ("/".equals(actionkey)||actionkey.startsWith("/index/")||"/index".equals(actionkey)) {
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
