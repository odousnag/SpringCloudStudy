package com.he.service;

import com.he.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author hewen
 */
public interface PaymentService {

    /**
     * 添加一条记录
     * @param payment
     * @return
     */
    int add(Payment payment);

    /**
     * 查询一条记录
     * @param id
     * @return
     */
    Payment queryPaymentById(@Param("id") Long id);

    /**
     * 正常访问成功 测试方法
     * @param id
     * @return
     */
    String paymentInfo_success(@Param("id") Long id);

    /**
     * 测试超时失败 测试方法
     * @param id
     * @return
     */
    String paymentInfo_Timeout(@Param("id") Long id);

    /**
     * 断路器，服务熔断
     * @param id
     * @return
     */
    String paymentCircuitBreaker(@Param("id")Long id);

}
