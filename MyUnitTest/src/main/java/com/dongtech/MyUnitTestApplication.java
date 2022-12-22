package com.dongtech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyUnitTestApplication {
    private static final Logger logger = LoggerFactory.getLogger(MyUnitTestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MyUnitTestApplication.class, args);
        runOk();
    }

    public static void runOk() {
        logger.info("                                                        \n" +
                "////////////////////////////////////////////////////////////////////\n" +
                "//                    UNIT-TEST 启动成功                           //\n" +
                "////////////////////////////////////////////////////////////////////");
    }
}
