package com.he.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hewen
 */
@Component
@FeignClient(value = "PROVIDER-HYSTRIX-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    /**
     * 测试正常连接
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/success/{id}")
    public String paymentInfo_success(@PathVariable(value = "id") Long id);

    /**
     * 超时测试
     * @return
     * @param id
     */
    @GetMapping(value = "/payment/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable(value = "id") Long id);
}
