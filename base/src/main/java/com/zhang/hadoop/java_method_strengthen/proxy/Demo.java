package com.zhang.hadoop.java_method_strengthen.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 16:51
 **/
public class Demo {

    @Test
    public void fun1() {

        Waiter manWaiter = new ManWaiter();//目标对象
        /*
         * 给出三个参数，来创建方法，得到代理对象
         */
        ClassLoader loader = this.getClass().getClassLoader();
        Class[] interfaces = {Waiter.class};
        InvocationHandler h = new WaiterInvocationHandler(manWaiter);//参数manWaiter表示目标对象
        // 得到代理对象，代理对象就是在目标对象的基础上进行了增强的对象！
        Waiter waiterProxy = (Waiter) Proxy.newProxyInstance(loader, interfaces, h);

        waiterProxy.serve();//前面添加“您好”，　后面添加“再见”
    }
}

