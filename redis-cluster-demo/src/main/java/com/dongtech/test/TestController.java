package com.dongtech.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: dongbao
 * @Date: 2018/9/11 16:48
 * @Description:
 */
@RestController
public class TestController {
    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping("/setTest")
    public Boolean setTest(){
        redisTemplate.opsForValue().set("k001","v001");
        redisTemplate.opsForValue().set("k002","v002");
        redisTemplate.opsForValue().set("k003","v003");
        if(redisTemplate.opsForValue().get("k001")!=null){
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping("/getTest")
    public boolean getTest(){
        String k001 = redisTemplate.opsForValue().get("k001");
        String k002 = redisTemplate.opsForValue().get("k002");
        String k003 = redisTemplate.opsForValue().get("k003");
        if(null!=k001&&null!=k002&&null!=k003){
            System.out.println("k001:"+k001);
            System.out.println("k002:"+k002);
            System.out.println("k003:"+k003);
            return true;
        }else{
            return false;
        }
    }
}
