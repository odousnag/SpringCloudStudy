package com.he.controller;

import com.he.entities.CommonResult;
import com.he.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hewen
 */

@RestController
public class OrderFeignController {

    @Resource
    PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/query/{id}")
    public CommonResult queryPaymentById(@PathVariable(value = "id") Long id){
        return paymentFeignService.queryPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/timeout")
    public String paymentFeignTimeout(){
        // OpenFeign客户端默认等待一秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
