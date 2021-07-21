package com.dongtech.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ioc")
public class IocController {
    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/getIoc")
    @ResponseBody
    public String getIoc(){
        Student stu01 = (Student) applicationContext.getBean("stu01");
        System.out.println(stu01.toString());
        return  stu01.toString();
    }
}
