package com.zhang.spring_cloud_nacos_consumer.common;

import com.zhang.spring_cloud_nacos_consumer.service.ProviderClient;
import org.springframework.stereotype.Component;

/**
 * @author: zhang yufei
 * @create: 2019-12-16 10:38
 **/
@Component
public class ProviderClientFallBack implements ProviderClient {
    @Override
    public String hi(String name) {
        return "sorry " + name;
    }
}