package com.he;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hewen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelRibbonProviderMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelRibbonProviderMain9003.class,args);
    }
}
