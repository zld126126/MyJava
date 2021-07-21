package com.dongtech.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: dongbao
 * @Date: 2018/8/29 14:36
 * @Description:远程调用
 * @FeignClient(value = "service-hi") service-hi是服务名
 * 基于service-feign工程进行改造，只需要在FeignClient的SchedualServiceHi接口的注解中加上fallback的指定类就行了：
 */
@FeignClient(value = "eureka-client",fallback = RemoteErrorService.class)
public interface RemoteService {
    @RequestMapping(value = "/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
