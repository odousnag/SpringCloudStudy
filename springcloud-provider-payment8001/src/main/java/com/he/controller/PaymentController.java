package com.he.controller;


import com.he.entities.CommonResult;
import com.he.entities.Payment;
import com.he.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author hewen
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/add")
    public CommonResult add(@RequestBody Payment payment){
        int result = paymentService.add(payment);
        log.info("插入的结果：" + result);

        if (result > 0){
            return new CommonResult(200,"插入成功，serverPort：" + serverPort, result);
        }else {
            return new CommonResult(440,"插入失败",null);
        }
    }

    @GetMapping(value = "/payment/query/{id}")
    public CommonResult queryPaymentById(@PathVariable(value = "id") Long id){
        Payment payment = paymentService.queryPaymentById(id);
        log.info("查询的结果：" + payment);

        if (!payment.equals(null)){
            return new CommonResult(200,"查询结果，serverPort：" + serverPort, payment);
        }else {
            return new CommonResult(440,"没有对应的记录",null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object getMessage(){
        // 微服务信息
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service" + service);
        }
        // 微服务名称下的具体实例
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            // 实例主机名称，服务id，端口号，URI地址
            log.info(instance.getHost()+"\t"+instance.getServiceId()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getLB(){
        return serverPort;
    }

    /**
     * 超时测试
     * @return
     */
    @GetMapping(value = "/payment/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
