package com.he.service.controller;

import com.he.service.MessageProvider;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hewen
 */
@RestController
public class SendMessageController {

    @Resource
    private MessageProvider provider;

    @GetMapping(value = "/senMessage")
    public Message senMessage(){
        return provider.send();
    }
}
