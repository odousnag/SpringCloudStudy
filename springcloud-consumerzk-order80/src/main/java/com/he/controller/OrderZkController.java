package com.he.controller;

import com.he.entities.CommonResult;
import com.he.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author hewen
 */

@RestController
@Slf4j
public class OrderZkController {


    public final static String PAYMENT_URL = "http://provider-payment-service";
    /**
     * Eureka 调用地址
     * public final static String PAYMENT_URL = "http://PROVIDER-PAYMENT-SERVICE";
     *
     * 单机调用地址
     * public final static String PAYMENT_URL = "http://localhost:8001";
     */

    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/consumer/payment/add")
    public CommonResult<Payment> add(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/query/{id}")
    public CommonResult<Payment> queryPayment(@PathVariable(value = "id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/query/" + id, CommonResult.class);
    }

    @GetMapping(value = "/consumer//payment/port")
    public String zookeeperServicePort(){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/port",String.class);
    }

}
