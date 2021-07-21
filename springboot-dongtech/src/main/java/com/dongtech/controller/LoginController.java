package com.dongtech.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongtech.bean.UserInfo;
import com.dongtech.mapper.UserInfoMapper;
import com.dongtech.resultbean.BaseResult;
import com.dongtech.shiro.MyShiroRealm;
import com.dongtech.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 登陆控制
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private UserInfoMapper userInfoMapper;

    private JSONObject js = null;

    /**
     * 根据用户名返回密码
     * @param username
     * @return
     */
    @RequestMapping(value="/getPasswordByUsername")
    @ResponseBody
    public String login(String username){
        //准备参数
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username",username);
        UserInfo userInfo = userInfoMapper.selectByUsername(map);
        if (null==userInfo){
            js = BaseResult.jsonInit("1", "null", "用户名不正确,请稍后重试");
        }else{
            js = BaseResult.jsonInit("0", userInfo.getPassword(), "用户名存在,返回密码");
        }
        logger.info(String.valueOf(js));
        return js.toString();
    }


    //http://localhost:8080/logincheck?username=dongbao&password=123456&rememberMe=false
    @RequestMapping(value="/logincheck")
    @ResponseBody
    public String login(String username, String password,String vcode,String remember){
        //后台判断逻辑

        //记住密码
        boolean rememberMe = false;
        if("1".equals(remember)){
            rememberMe = true;
        }

        //用户名和密码校验
        if (null==username||""==username||null==password||""==password){
            js = BaseResult.jsonInit("1","null", "用户名或密码不正确,请稍后重试");
        }else{
            //准备参数
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("username",username);
            map.put("password",password);
            //查询数据库
            UserInfo userInfo = userInfoMapper.selectByUsernameAndPassword(map);
            if (null==userInfo){
                js = BaseResult.jsonInit("1","null", "用户名或密码不正确,请稍后重试");
            }else{
                //先清除缓存
                new MyShiroRealm().clearCachedAuthorizationInfo();
                UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
                SecurityUtils.getSubject().login(token);
                js = BaseResult.jsonInit("0",username, "登陆成功");
            }
        }
        logger.info(String.valueOf(js));
        return js.toString();
    }

    @RequestMapping(value="/login")
    public String login(){
        return "/login";
    }

    //http://localhost:8080/index 如果没有token信息则显示403如果有则显示home
    @RequestMapping(value="/index")
    public String index(){
        Subject subject = SecurityUtils.getSubject();
        UserInfo principal = (UserInfo)subject.getPrincipal();
        if(null==principal){
            //如果没有登陆信息,则登陆
            return "/login";
        }else{
            //如果有登陆信息则进入主页
            return "menu/index";
        }
    }

}
