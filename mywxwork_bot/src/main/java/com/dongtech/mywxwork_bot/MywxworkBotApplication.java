package com.dongtech.mywxwork_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MywxworkBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MywxworkBotApplication.class, args);
    }

}
