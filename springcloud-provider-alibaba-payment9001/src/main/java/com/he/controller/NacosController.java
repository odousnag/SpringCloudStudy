package com.he.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hewen
 */

@RestController
public class NacosController {

    @Value("${server.port}")
    private String serverPort;

    public class EchoController {
        @GetMapping(value = "/nacos")
        public String echo() {
            return "Hello Nacos Discovery，serverPort：" + serverPort;
        }
    }
}
