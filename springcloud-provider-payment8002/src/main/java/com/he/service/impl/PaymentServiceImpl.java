package com.he.service.impl;

import com.he.dao.PaymentDao;
import com.he.entities.Payment;
import com.he.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hewen
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;

    @Override
    public int add(Payment payment) {
        return paymentDao.add(payment);
    }

    @Override
    public Payment queryPaymentById(Long id) {
        return paymentDao.queryPaymentById(id);
    }
}
