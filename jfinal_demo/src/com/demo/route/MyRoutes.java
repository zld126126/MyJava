package com.demo.route;

import com.demo.blog.BlogController;
import com.demo.index.IndexController;
import com.jfinal.config.Routes;

public class MyRoutes extends Routes {
	/**
	 * 刘磊移动端专用
	 */
	@Override
	public void config() {
		super.add("/", IndexController.class, "/index");	// 第三个参数为该Controller的视图存放路径
		super.add("/blog", BlogController.class);			// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
		super.add("/index",IndexController.class);
	}

}
