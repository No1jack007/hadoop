package com.zhang.hadoop.java_method_strengthen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 16:50
 **/

class WaiterInvocationHandler implements InvocationHandler {
    private Waiter waiter;//目标对象

    public WaiterInvocationHandler(Waiter waiter) {
        this.waiter = waiter;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("您好！");
        this.waiter.serve();//调用目标对象的目标方法
        System.out.println("再见！");
        return null;
    }
}

