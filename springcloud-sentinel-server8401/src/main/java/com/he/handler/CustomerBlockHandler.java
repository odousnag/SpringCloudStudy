package com.he.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.he.entities.CommonResult;
import com.he.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author hewen
 */

@Component
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(444,"自定义规则异常处理",new Payment(4L,"004"));
    }
}
