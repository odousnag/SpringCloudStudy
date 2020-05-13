package com.he;

import com.IRule.SelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


/**
 * @author hewen
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "PROVIDER-PAYMENT-SERVICE",configuration = SelfRule.class)
public class OrderMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain.class,args);
    }
}
