package com.dongtech;

import com.dongtech.controller.UserProfileController;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.dongtech.mapper")	//这里mapper是你的mybatis的mapper目录。
@SpringBootApplication
public class DongtechApplication{

    private static final Logger logger = LoggerFactory.getLogger(DongtechApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(DongtechApplication.class, args);
        runOk();

    }
    public static void runOk(){
        logger.info("////////////////////////////////////////////////////////////////////\n" +
                "//              -------     ----   --   --    -----               //\n" +
                "//              |   _  \\   / __ \\  | \\   |   /   ___              //\n" +
                "//              |  |_|  | | |__| | |  \\  |  |     |               //\n" +
                "//              |      /   \\    /  |   \\ |   \\    |               //\n" +
                "//              -------     ----   -    --    -----               //\n" +
                "//                          启动成功                                //\n" +
                "////////////////////////////////////////////////////////////////////");
    }
}
