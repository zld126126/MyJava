package com.dongtech.config;

import com.dongtech.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisWriteConfiguration {

    @Bean(name = "redisWriteTemplate")
    public StringRedisTemplate redisTemplate(@Value("${spring.redis-write.host}") String hostName,
                                             @Value("${spring.redis-write.port}") int port,
                                             @Value("${spring.redis-write.password}") String password,
                                             @Value("${spring.redis-write.testOnBorrow}") boolean testOnBorrow,
                                             @Value("${spring.redis.jedis.pool.max-idle}") int maxIdle,
                                             @Value("${spring.redis.jedis.pool.max-active}") int maxTotal,
                                             @Value("${spring.redis.database}") int index,
                                             @Value("${spring.redis.jedis.pool.max-wait}") long maxWaitMillis) {
        StringRedisTemplate temple = new StringRedisTemplate();
        temple.setConnectionFactory(connectionFactory(hostName, port, password, maxIdle, maxTotal, index, maxWaitMillis, testOnBorrow));

        return temple;
    }

    public RedisConnectionFactory connectionFactory(String hostName, int port, String password, int maxIdle,
                                                    int maxTotal, int index, long maxWaitMillis, boolean testOnBorrow) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(hostName);
        jedis.setPort(port);
        if (StringUtils.isNotEmpty(password)) {
            jedis.setPassword(password);
        }
        if (index != 0) {
            jedis.setDatabase(index);
        }
        jedis.setPoolConfig(poolCofig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow));
        // 初始化连接pool
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;

        return factory;
    }

    public JedisPoolConfig poolCofig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        return poolCofig;
    }
}