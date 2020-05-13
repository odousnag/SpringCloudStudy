package com.he.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author hewen
 */

public interface LoadBalancer {

    /**
     * 将服务列表下的具体实例 放在一个集合 里进行调用
     *
     * @param instanceList ：服务列表下的具体实例
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> instanceList);


}
