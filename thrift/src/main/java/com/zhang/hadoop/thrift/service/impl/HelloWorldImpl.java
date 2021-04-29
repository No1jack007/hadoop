package com.zhang.hadoop.thrift.service.impl;

/**
 * @author ：zhangyufei.zhang
 * @date ：Created in 2021-04-13 11:32
 * @description：
 */
import com.zhang.hadoop.thrift.service.HelloWorldService;
import org.apache.thrift.TException;

public class HelloWorldImpl implements HelloWorldService {

    public HelloWorldImpl() {
    }

    @Override
    public String sayHello(String username) throws TException {
        return "Hi," + username + " welcome to thrift world";
    }

}