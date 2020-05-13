package com.he.service;

import org.springframework.stereotype.Component;

/**
 * @author hewen
 */

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_success(Long id) {
        return "服务端连接失败";
    }

    @Override
    public String paymentInfo_timeout(Long id) {
        return "服务端连接失败，请稍后重试";
    }
}
