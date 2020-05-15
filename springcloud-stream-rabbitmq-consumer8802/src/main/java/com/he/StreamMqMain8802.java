package com.he;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hewen
 */

@SpringBootApplication
@EnableEurekaClient
public class StreamMqMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMqMain8802.class,args);
    }
}
