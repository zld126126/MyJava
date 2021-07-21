package com.dongtech.config;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Auther: dongbao
 * @Date: 2018/9/10 15:13
 * @Description:
 */
public abstract class AbRedisConfiguration {
    protected StringRedisTemplate temple;

    public void setData(String key, String value) {
        getTemple().opsForValue().set(key, value);
    }

    public String getData(String key) {
        return getTemple().opsForValue().get(key);
    }

    public StringRedisTemplate getTemple() {
        return temple;
    }
}
