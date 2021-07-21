package com.dongtech.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 学生实体类
 * @author: liandong
 * @create: 2019-02-27 20:20
 **/
@Data
public class Children implements Serializable {
    private String name;
    private Integer age;
    private String gender;

    public Children(String name, Integer age, String gender) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
