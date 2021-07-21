package com.dongtech.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongtech.constants.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 验证码Controller
 * 
 * @author 东宝
 * 
 */
@Controller
public class JcaptchaController {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	public static final int WIDTH = 120;
	
    public static final int HEIGHT = 50;

    /**
     * 生成图形验证码方法
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value="/jcaptcha/captcha")
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
    	//生成6位随机验证码
    	String captcha = this.getRandomCode(5);
		try {
			ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
			// 创建缓存
	        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	        // 获得画布
	        Graphics g = bufferedImage.getGraphics();
	        // 设置颜色
	        g.setColor(Color.WHITE);
	        // 填充区域
	        g.fillRect(0, 0, WIDTH, HEIGHT);
	        // 设置边框颜色
	        g.setColor(Color.LIGHT_GRAY);
	        // 边框区域
	        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	        // 设置颜色
	        g.setColor(Color.BLUE);
	        // 设置字体
	        g.setFont(new Font("Times New Roman", Font.ITALIC, 32));
	        // 画数据
	        g.drawString(captcha, 10, 38);
			
	        //将验证码放置到session中
	        request.getSession().setAttribute(Constants.CAPTCHA, captcha);
	        
			ImageIO.write(bufferedImage, "jpeg", jpegOutputStream);
			byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
			
			//将验证码输出到页面
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0L);
			response.setContentType("image/jpeg");
			ServletOutputStream respOs = response.getOutputStream();
			respOs.write(captchaChallengeAsJpeg);
			respOs.flush();
			respOs.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("生成图形验证码图片发生异常: {}", e);
		}
	}
    
    /**
     * 生成0-9，a-z，A-Z的随机6位字符
     * 
     * @param count 要生成的字符个数
     * @return
     */
    private String getRandomCode (int count) {
    	String[] arry = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};
    	StringBuilder result = new StringBuilder("");
    	for (int i=0; i<count; i++) {
    		int index = (int)Math.round(Math.random() * 61);
    		result.append(arry[index]);
    	}
    	return result.toString();
    }
}