package com.zhang.hadoop.thrift.service;

import org.apache.thrift.TException;

/**
 * @author ：zhangyufei.zhang
 * @date ：Created in 2021-04-13 11:33
 * @description：
 */
service  HelloWorldService {
        string sayHello(1:string username)
}