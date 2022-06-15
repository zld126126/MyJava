package com.gy.kafkalib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkalibApplication {
    private static final Logger logger = LoggerFactory.getLogger(KafkalibApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(KafkalibApplication.class, args);
        runOk();
    }

    public static void runOk(){
        logger.info("                                                        \n" +
        "////////////////////////////////////////////////////////////////////\n" +
        "//               -----      \\     /                              //\n" +
        "//              /  ___        \\ /              GY-KAFKA          //\n" +
        "//             |     |         |        GY-KAFKA                 //\n" +
        "//             \\     |         |                     GY-KAFKA    //\n" +
        "//              -------        |                                 //\n" +
        "//                          启动成功                              //\n" +
        "////////////////////////////////////////////////////////////////////");
    }
}
