package com.dongtech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用启动类
 *
 * Created by Jaycekon on 20/09/2017.
 */
// Spring Boot 应用的标识
@SpringBootApplication
public class ClientApplication {

    private static final Logger logger = LoggerFactory.getLogger(ClientApplication.class);


    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(ClientApplication.class, args);
        runOk();
    }

    public static void runOk(){
        logger.info("\n"+
                "////////////////////////////////////////////////////////////////////\n" +
                "//              -------     ----   --   --    -----               //\n" +
                "//              |   _  \\   / __ \\  | \\   |   /   ___              //\n" +
                "//              |  |_|  | | |__| | |  \\  |  |     |               //\n" +
                "//              |      /   \\    /  |   \\ |   \\    |               //\n" +
                "//              -------     ----   -    --    -----               //\n" +
                "//                      服务消费者启动成功                           //\n" +
                "////////////////////////////////////////////////////////////////////");
    }
}
