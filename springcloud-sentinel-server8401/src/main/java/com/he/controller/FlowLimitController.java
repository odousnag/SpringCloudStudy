package com.he.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hewen
 */

@RestController
public class FlowLimitController {

    @GetMapping(value = "/test1")
    public String test1(){
        return "111";
    }

    @GetMapping(value = "/test2")
    public String test2(){
        return "222";
    }
}
