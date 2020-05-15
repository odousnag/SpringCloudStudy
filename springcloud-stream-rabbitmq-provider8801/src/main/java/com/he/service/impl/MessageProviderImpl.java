package com.he.service.impl;

import com.he.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
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
     * @ InboundChannelAdapter
     * 作用：表示定义的方法能产生消息
     * fixedDelay：多少毫秒发送1次
     */
    @Override
    @InboundChannelAdapter(channel = Source.OUTPUT,poller = @Poller(fixedDelay = "10000"))
    public Message<String> send() {
        String serial = UUID.randomUUID().toString();
        return MessageBuilder.withPayload(serial)
                .build();
    }
}
