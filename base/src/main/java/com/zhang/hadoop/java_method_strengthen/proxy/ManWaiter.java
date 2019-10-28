package com.zhang.hadoop.java_method_strengthen.proxy;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 16:49
 **/

public class ManWaiter implements Waiter {
    public void serve() {
        System.out.println("服务中...");
    }
}
