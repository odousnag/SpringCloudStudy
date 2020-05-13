package com.he.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hewen
 */

@Component
public class MyLb implements LoadBalancer {

    /**
     * 设置初始值为 0
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 获得当前值
     * @return ：成功就返回 下标
     */
    public final int getAtomicInteger(int module){
        /*
        current: 当前值
        next：下标
        nextSize：next的大小值
         */
        int current;
        int next;
        int nextSize;
        do {
            current = this.atomicInteger.get();
            /*
             如果当前值 current 大于了 2147483647，就重新从 0 开始计算
             2147483647的意义是 Integer 整型的最大值，防止越界
             否则 nextSize 的值就等于 current + 1
             */
            nextSize = current >= 2147483647 ? 0 : current + 1;
            next = nextSize % module;
            // 取反，不成功就一直比较判断，成功就返回 false ，跳出循环，返回 next
        }while (!atomicInteger.compareAndSet(current,nextSize));
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> instanceList) {
        ServiceInstance instance = null;
        if (instanceList == null || instanceList.size() <= 0){
            return null;
        }else {
            // 服务实例列表多少
            int serverCount = instanceList.size();
            // 获取下标，取余数
            int index = getAtomicInteger(serverCount);

            return instanceList.get(index);
        }
    }
}
