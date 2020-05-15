package com.he.service;

import org.springframework.messaging.Message;

/**
 * @author hewen
 */

public interface MessageProvider {
    /**
     * 消息发送
     *  @return ：返回值
     */
    Message<?> send();
}
