package com.dongtech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: dongbao
 * @Date: 2018/8/29 15:06
 * @Description:通过之前注入ioc容器的restTemplate来消费service-hi服务的“/hi”接口
 */
@Service
public class TestService {
    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name) {
        return restTemplate.getForObject("http://EUREKA-CLIENT/hi?name="+name,String.class);
    }
}
