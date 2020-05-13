package com.he.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author hewen
 */
@Component
@Slf4j
public class LogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求的参数
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (username == null){
            log.info("用户名为空，找不到该用户");
            // 设置状态码
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            return exchange.getResponse().setComplete();
        }
        // 返回
        log.info("进入拦截器");
        return chain.filter(exchange);
    }

    /**
     * 加载过滤器的顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
