package com.he.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author hewen
 */

@RestController
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    @Value(value = "${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping(value = "/consumer/nacos")
    public String echo() {
        return restTemplate.getForObject(serverURL+"/nacos",String.class);
    }
}
