package com.he.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.he.entities.CommonResult;
import com.he.entities.Payment;
import com.he.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author hewen
 */

@RestController
public class RateLimitController {

    @GetMapping(value = "/resource")
    @SentinelResource(value = "resource",blockHandler = "Deal_resource")
    public CommonResult resource(){
        return new CommonResult(200,"资源名限流测试",new Payment(1L,"001"));
    }

    public CommonResult Deal_resource(BlockException e){
        return new CommonResult(444,e.getClass().getCanonicalName());
    }

    @GetMapping(value = "/url")
    @SentinelResource(value = "url")
    public CommonResult url(){
        return new CommonResult(200,"url限流测试",new Payment(2L,"002"));
    }

    @GetMapping(value = "/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"自定义规则",new Payment(3L,"003"));
    }
}
