package com.dongtech.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: dongbao
 * @Date: 2018/8/29 14:36
 * @Description:远程调用
 * @FeignClient(value = "service-hi") service-hi是服务名
 */
@FeignClient(value = "eureka-client")
public interface RemoteService {
    @RequestMapping(value = "/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
