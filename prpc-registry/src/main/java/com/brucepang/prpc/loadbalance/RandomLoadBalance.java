package com.brucepang.prpc.loadbalance;

import com.brucepang.prpc.common.ServiceInfo;
import org.apache.curator.x.discovery.ServiceInstance;

import java.util.List;
import java.util.Random;

/**
 * @author BrucePang
 */
public class RandomLoadBalance extends AbstractLoadBalance{
    @Override
    protected ServiceInstance<ServiceInfo> doSelect(List<ServiceInstance<ServiceInfo>> services) {
        int len = services.size();
        Random random = new Random();
        return services.get(random.nextInt(len));
    }
}
