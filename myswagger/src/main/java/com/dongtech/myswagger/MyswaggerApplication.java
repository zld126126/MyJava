package com.dongtech.myswagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyswaggerApplication {
    private static final Logger logger = LoggerFactory.getLogger(MyswaggerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MyswaggerApplication.class, args);
        runOk();
    }

    public static void runOk() {
        logger.info("                                                        \n" +
                "////////////////////////////////////////////////////////////////////\n" +
                "//                    My-Swagger 启动成功                          //\n" +
                "////////////////////////////////////////////////////////////////////");
    }
}
