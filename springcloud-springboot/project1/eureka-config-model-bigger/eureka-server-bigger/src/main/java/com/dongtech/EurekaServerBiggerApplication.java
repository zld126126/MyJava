package com.dongtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerBiggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerBiggerApplication.class, args);
    }
}
