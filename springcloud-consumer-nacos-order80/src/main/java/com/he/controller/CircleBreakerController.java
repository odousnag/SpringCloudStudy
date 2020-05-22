package com.he.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.he.entities.CommonResult;
import com.he.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author hewen
 */

@RestController
@Slf4j
public class CircleBreakerController {

    public final static String PAYMENT_URL = "http://nacos-provider";

    @Resource
    private RestTemplate restTemplate;

    /**
     * @SentinelResource(value = "fallback")
     * @SentinelResource(value = "fallback",fallback = "FallBackHandler")  fallback只负责业务异常
     * @SentinelResource(value = "fallback",blockHandler = "BlockHandler") blockHandler只负责sentinel控制台配置异常
     * @SentinelResource(value = "fallback",fallback = "FallBackHandler",blockHandler = "BlockHandler")
     *
     */
    @GetMapping(value = "/consumer/fallback/{id}")
    @SentinelResource(value = "fallback")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(PAYMENT_URL + "/payment/query/" + id, CommonResult.class);
        if (id == 4){
            throw new IllegalArgumentException("非法参数异常");
        }else if (result.getData() == null){
            throw new NullPointerException("该 ID 没有对应的记录，空指针异常");
        }
        return result;
    }
}
