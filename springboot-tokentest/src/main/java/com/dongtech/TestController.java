package com.dongtech;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: dongbao
 * @Date: 2018/9/3 11:29
 * @Description:
 */
@Controller
public class TestController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/create")
    public String create(HttpServletRequest request, Model model){
        boolean flag = TokenTools.createToken(request, "dongserver");
        if(flag){
            model.addAttribute("msg","true");
        }
        return "create";
    }

    @RequestMapping("/check")
    public String check(HttpServletRequest request, Model model){
        boolean flag = TokenTools.judgeTokenIsEqual(request, "dongclient", "dongserver");
        if(flag){
            model.addAttribute("msg","token检查成功");
        }else{
            model.addAttribute("msg","token检查成功");
        }
        return "check";
    }
}
