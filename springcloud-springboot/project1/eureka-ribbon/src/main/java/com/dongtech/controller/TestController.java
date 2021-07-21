package com.dongtech.controller;

import com.dongtech.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: dongbao
 * @Date: 2018/8/29 15:07
 * @Description:在controller中用调用HelloService 的方法
 */
@RestController
public class TestController {
    @Autowired
    TestService testService;
    //远程调用
    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return testService.hiService(name);
    }
}
