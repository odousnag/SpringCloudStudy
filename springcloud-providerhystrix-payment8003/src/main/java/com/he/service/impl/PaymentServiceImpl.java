package com.he.service.impl;

import com.he.dao.PaymentDao;
import com.he.entities.Payment;
import com.he.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    @Override
    public String paymentInfo_success(Long id) {
        return "Success线程池" + Thread.currentThread().getName() + "paymentInfo，id：" + id;
    }

    /**
     * 此时当这个程序跑满，要设置一个方法来进行兜底，fallbackMethod = "TimeoutHandler"
     * @ HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
     * 设置这个线程的超时时间为 3 秒，3 秒后就会去到回调的方法 TimeoutHandler
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_Timeout(Long id) {
        try {
            // 设置为 5 秒进行服务提供者自测
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Timeout：" + Thread.currentThread().getName() + "paymentInfo";
    }


    /**
     * 超时后的回调处理方法
     * @param id
     * @return
     */
    public String TimeoutHandler(Long id){
        return "提供者 TimeoutHandler：" + Thread.currentThread().getName() + "系统繁忙或者运行超时，请稍后再试";
    }


    // 服务熔断
    /**
     * 断路器，服务熔断，如果出错，就由 paymentCircuitBreaker_Fallback 这个方法来回调
     * (name = "circuitBreaker.enabled",value = "true") ：是否开启断路器
     * (name = "circuitBreaker.requestVolumeThreshold",value = "10") ：请求次数
     * (name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000") ： 时间窗口期
     * (name = "circuitBreaker.errorThresholdPercentage",value = "60") ： 失败率达到多少后跳闸
     *
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_Fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String paymentCircuitBreaker(Long id){
        if (id < 0){
            throw new RuntimeException("id 不能为负数");
        }
        return "提供者CircuitBreaker：" + Thread.currentThread().getName();
    }

    /**
     * 断路器回调的方法
     * @param id
     * @return
     */
    public String paymentCircuitBreaker_Fallback(Long id){
        return "id 不能为负数，id：" + id;
    }
}
