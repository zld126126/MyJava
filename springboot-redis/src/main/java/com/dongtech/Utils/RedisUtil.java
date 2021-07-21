package com.dongtech.Utils;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisUtil {
    @Resource
    RedisTemplate<String, String> redisTemplate;

    public void set(String key,List list){
        String value = JSON.toJSONString(list);
        redisTemplate.opsForValue().set(key,value);
    }

    public List get(String key){
        String value = (String) redisTemplate.opsForValue().get(key);
        List list = (List) JSON.parse(value);
        return list;
    }
}
