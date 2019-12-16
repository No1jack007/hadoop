package com.zhang.spring_cloud_nacos_consumer.service;

import com.zhang.spring_cloud_nacos_consumer.common.ProviderClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zhang yufei
 * @create: 2019-12-02 15:34
 **/
@FeignClient(value = "nacos-provider",fallback = ProviderClientFallBack.class)
public interface ProviderClient {

    @GetMapping("/hi")
    String hi(@RequestParam(value = "name", defaultValue = "forezp", required = false) String name);
}
