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
    public int add(Payment payment);

    /**
     * 查询一条记录
     * @param id
     * @return
     */
    public Payment queryPaymentById(@Param("id") Long id);
}
