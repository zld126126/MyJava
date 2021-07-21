package com.dongtech.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AopController {
    @RequestMapping(value = "/say",method = RequestMethod.GET)
    public String sayHello(String name){
        System.out.println("hello " + name);
        return "hello " + name;
    }
}
