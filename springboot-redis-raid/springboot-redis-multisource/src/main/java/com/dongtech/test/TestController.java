package com.dongtech.test;

import com.dongtech.config.RedisRead;
import com.dongtech.config.RedisWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: dongbao
 * @Date: 2018/9/10 15:15
 * @Description:
 */
@RestController
public class TestController {
    @Autowired
    private RedisWrite redisWrite;
    @Autowired
    private RedisRead redisRead;

    @RequestMapping("/setAge")
    public void setAge(){
        redisWrite.setData("age","24");
    }

    @RequestMapping("/getAge")
    public void getAge(){
        String age = redisRead.getData("age");
        System.out.println("age:"+age);
    }
}
