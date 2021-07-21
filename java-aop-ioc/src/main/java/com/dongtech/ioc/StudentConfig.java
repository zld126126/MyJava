package com.dongtech.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    //将此返回的值生成一个bean，id为stu01
    @Bean("stu01")
    public Student stu01() {
        Student stu = new Student();
        stu.setId(1);
        stu.setName("张三");
        return stu;
    }
}
