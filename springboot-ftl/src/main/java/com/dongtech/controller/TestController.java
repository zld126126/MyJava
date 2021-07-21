package com.dongtech.controller;

import com.dongtech.entity.Children;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 测试控制类
 * @author: liandong
 * @create: 2019-02-27 20:09
 **/
@Controller
public class TestController {
    @RequestMapping("/hello")
    @ResponseBody
    public String toTest() {
        return "Hello World";
    }

    //localhost:9090
    @RequestMapping("/")
    public String index(Model model) {
        //1.迭代list
        List<Children> childrenList= new ArrayList<Children>();
        childrenList.add(new Children("张三", 25, "男"));
        childrenList.add(new Children("李四", 28, "男"));
        childrenList.add(new Children("小红", 22, "女"));


        //2.迭代map
        Map<String,Object> userMap = new HashMap<String,Object>();
        userMap.put("address","beijing");
        userMap.put("birthday","12/26");
        userMap.put("phone","133********");

        //3.迭代常规对象
        model.addAttribute("flag",1);
        model.addAttribute("home","china");

        //4
        model.addAttribute("condition",5);

        //list、map添加进model
        model.addAttribute("childrenList",childrenList);
        model.addAttribute("userMap",userMap);
        return "index";
    }
}
