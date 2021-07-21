package com.dongtech.config;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Auther: dongbao
 * @Date: 2018/9/10 15:14
 * @Description:
 */
@Component
public class RedisRead extends AbRedisConfiguration{
    @Resource(name = "redisReadTemplate")
    private StringRedisTemplate temple;

    public StringRedisTemplate getTemple() {
        return temple;
    }
}
