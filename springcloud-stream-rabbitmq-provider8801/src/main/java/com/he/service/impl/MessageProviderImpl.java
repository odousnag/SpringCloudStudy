package com.he.service.impl;

import com.he.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import java.util.UUID;

/**
 * @author hewen
 * @ EnableBinding 定义消息的推送管道
 */
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {

    /**
     * @ InboundChannelAdapter(channel = Source.OUTPUT) 消息发送管道
     */
    @Override
    @InboundChannelAdapter(channel = Source.OUTPUT)
    public Message<?> send() {
        String serial = UUID.randomUUID().toString();
        return MessageBuilder.withPayload(serial)
                .build();
    }
}
