package com.dongtech;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: dongbao
 * @Date: 2018/9/3 10:43
 * @Description:
 */
@Controller
public class TestController {
    @RequestMapping("/index")
    public String index (Model model) {
        model.addAttribute("data", "Springboot集成Thymeleaf");
        return "index";
    }
}
