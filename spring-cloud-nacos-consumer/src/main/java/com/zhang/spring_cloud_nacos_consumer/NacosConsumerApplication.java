package com.zhang.spring_cloud_nacos_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zhang yufei
 * @create: 2019-12-02 10:56
 **/

@SpringBootApplication
//使用Nacos服务注册和发现
@EnableDiscoveryClient
@ComponentScan(basePackages={"com.zhang"})
//开启服务消费者feign
@EnableFeignClients
//开启断路器Hystrix
@EnableHystrix
//开启路由网关zuul
@EnableZuulProxy
public class NacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
