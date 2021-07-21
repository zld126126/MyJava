package com.dongtech.controller;

import com.dongtech.Utils.RedisUtil;
import com.dongtech.entity.Person;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Resource
    RedisUtil redisUtil;

    @RequestMapping("/sayHello")
    @ResponseBody
    public String sayHello(){
        return "Hello,World!";
    }
    @ResponseBody
    @RequestMapping("/setRedis")
    public String setRedis(){
        Person p1 = new Person();
        p1.setAge(11);
        p1.setName("东宝");
        Person p2 = new Person();
        p2.setAge(31);
        p2.setName("龙宝");
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        System.out.println(list);
        redisUtil.set("plist",list);
        return list.toString();
    }
    @ResponseBody
    @RequestMapping("/getRedis")
    public String getRedis(){
        List plist = redisUtil.get("plist");
        return plist.toString();
    }

}
