package com.he.service;

import com.he.entities.CommonResult;
import com.he.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hewen
 */
@Component
@FeignClient(value = "provider-payment-service")
public interface PaymentFeignService {

    /**
     * 查询一条记录
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/query/{id}")
    CommonResult<Payment> queryPaymentById(@PathVariable(value = "id") Long id);

    /**
     * 超时测试
     * @return
     */
    @GetMapping(value = "/payment/timeout")
    String paymentFeignTimeout();
}
