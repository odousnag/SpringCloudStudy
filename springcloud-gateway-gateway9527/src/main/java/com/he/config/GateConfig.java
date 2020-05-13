package com.he.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hewen
 */

@Configuration
public class GateConfig {

    /**
     * 测试通过网关 跳转到 百度新闻 的页面 http://news.baidu.com/guonei
     * 配置了一个 id 为 payment-route3 的路由规则
     * 当访问地址为 http://localhost:9527/guonei 会自动转发到 http://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route( "payment-route3",r -> {
                    return r.path("/guonei")
                            .uri("http://news.baidu.com/guonei");
                })
                .build();
    }
    
}
