package com.dongtech;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class EurekaZipkinService1Application {

    private static final Logger LOG = Logger.getLogger(EurekaZipkinService1Application.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(EurekaZipkinService1Application.class, args);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String callHome(){
        LOG.log(Level.INFO, "calling trace service-1  ");
        return restTemplate.getForObject("http://localhost:8775/miya", String.class);
    }
    @RequestMapping("/info")
    public String info(){
        LOG.log(Level.INFO, "calling trace service-1 ");

        return "i'm service-1";

    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }


}
