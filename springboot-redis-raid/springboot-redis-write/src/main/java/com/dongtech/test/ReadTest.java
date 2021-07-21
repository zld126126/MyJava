package com.dongtech.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: dongbao
 * @Date: 2018/9/10 14:22
 * @Description:测试读取
 */
@RestController
public class ReadTest {
    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping("/getName")
    public String getName(){
        System.out.println(redisTemplate.opsForValue().get("name"));
        return redisTemplate.opsForValue().get("name");
    }
}
