package com.he.controller;

import com.he.entities.CommonResult;
import com.he.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hewen
 */

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "CommandFallBack")
public class OrderHystrixController {

    @Resource
    PaymentHystrixService paymentHystrixService;


    /**
     * 测试正常连接
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/payment/success/{id}")
    public String paymentInfo_success(@PathVariable(value = "id") Long id){
        String result = paymentHystrixService.paymentInfo_success(id);
        log.info(result);
        return result;
    }


    /**
     * 超时测试
     * @return
     *
     * @ HystrixCommand(fallbackMethod = "TimeoutHandler",commandProperties = {
     *             @ HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
     *     })
     */
    @GetMapping(value = "/consumer/payment/timeout/{id}")
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable(value = "id") Long id){
        String result = paymentHystrixService.paymentInfo_timeout(id);
        log.info(result);
        return result;
    }

    /**
     * 超时后的回调处理方法
     * @return
     */
    public String TimeoutHandler(){
        return "消费者 TimeoutHandler：" + Thread.currentThread().getName() + "系统繁忙或者运行超时，请稍后再试";
    }

    /**
     * 全局配置 FallBack 方法
     * @return
     */
    public String CommandFallBack(){
        return "全局异常处理信息，请稍后再试";
    }
}
