package com.dongtech.service;

import org.springframework.stereotype.Component;

/**
 * @Auther: dongbao
 * @Date: 2018/8/29 15:45
 * @Description:
 */
@Component
public class RemoteErrorService implements RemoteService{

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
