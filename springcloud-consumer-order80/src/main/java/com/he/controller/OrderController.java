package com.he.controller;

import com.he.entities.CommonResult;
import com.he.entities.Payment;
import com.he.lb.LoadBalancer;
import com.he.lb.MyLb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author hewen
 */

@RestController
@Slf4j
public class OrderController {

    public final static String PAYMENT_URL = "http://PROVIDER-PAYMENT-SERVICE";
    //public final static String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/consumer/payment/postForObject/add")
    public CommonResult<Payment> add(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, CommonResult.class);
    }

    @PostMapping(value = "/consumer/payment/postForEntity/add")
    public CommonResult<Payment> addTwo(Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/add", payment, CommonResult.class);
        // 判断状态码
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"添加错误");
        }
    }

    @GetMapping(value = "/consumer/payment/getForObject/{id}")
    public CommonResult<Payment> queryPayment(@PathVariable(value = "id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/query/" + id, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> queryPaymentTwo(@PathVariable(value = "id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/query/" + id, CommonResult.class);
        // 判断状态码
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"查询错误");
        }
    }

    @GetMapping(value = "/consumer/payment/com.he.lb")
    public String getLB(){
        // 微服务名称下的具体实例
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT-SERVICE");

        // 把服务列表放进去
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri + "/payment/com.he.lb", String.class);
    }

}
