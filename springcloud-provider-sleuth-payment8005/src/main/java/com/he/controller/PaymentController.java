package com.he.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hewen
 */

@RestController
public class PaymentController {

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "链路测试";
    }
}
