package com.dongtech.controller;

import com.dongtech.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: dongbao
 * @Date: 2018/8/29 14:40
 * @Description:
 */
@RestController
public class TestController {
    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    RemoteService remoteService;
    //http://localhost:8765/hi?name=dong
    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return remoteService.sayHiFromClientOne( name );
    }
}
