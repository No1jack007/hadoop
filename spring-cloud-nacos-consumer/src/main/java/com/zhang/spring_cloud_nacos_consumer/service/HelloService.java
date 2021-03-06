package com.zhang.spring_cloud_nacos_consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zhang yufei
 * @create: 2019-12-04 11:31
 **/
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://nacos-provider/hi??name="+name,String.class);
    }

    /*
    Hystrix熔断
     */
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }


}
