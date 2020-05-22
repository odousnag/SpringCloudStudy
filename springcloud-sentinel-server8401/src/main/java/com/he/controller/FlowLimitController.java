package com.he.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author hewen
 */

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping(value = "/test1")
    public String test1(){
        log.info("测试");
        return "111";
    }

    @GetMapping(value = "/test2")
    public String test2(){
        return "222";
    }

    @GetMapping(value = "/test3")
    public String test3(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("测试");
        return "333";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_HotKey")
    public String toHotKey(
            @RequestParam(value = "p1", required = false) String p1,
            @RequestParam(value = "p2", required = false) String p2){
        return "热点测试";
    }

    public String deal_HotKey(String p1, String p2, BlockException blockException){
        return "deal_HotKey";
    }
}
