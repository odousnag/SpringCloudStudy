package com.IRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hewen
 */

@Configuration
public class SelfRule {

    @Bean
    public IRule myselfRule(){
        //定义为 随机
        return new RandomRule();
    }
}
