package com.dongtech.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: dongbao
 * @Date: 2018/9/10 14:12
 * @Description:测试写入
 */
@RestController
public class WriteTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping("/setName")
    public String setName(){
        redisTemplate.opsForValue().set("name","东宝");
        System.out.println(redisTemplate.opsForValue().get("name"));
        return redisTemplate.opsForValue().get("name");
    }


}
