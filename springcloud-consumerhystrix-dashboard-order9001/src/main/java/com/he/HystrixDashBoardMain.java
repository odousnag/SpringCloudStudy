package com.he;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author hewen
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashBoardMain {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashBoardMain.class,args);
    }
}
