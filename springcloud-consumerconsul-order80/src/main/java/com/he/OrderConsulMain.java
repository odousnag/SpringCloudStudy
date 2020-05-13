package com.he;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author hewen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain.class,args);
    }
}
