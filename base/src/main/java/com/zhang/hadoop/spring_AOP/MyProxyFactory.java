package com.zhang.hadoop.spring_AOP;

import java.lang.reflect.Proxy;

/**
 * @author: zhang yufei
 * @create: 2019-10-30 15:54
 **/
public class MyProxyFactory {

    public static Object getObject(Object target) {

        MyinvocationHandler MyinvocationHandler = new MyinvocationHandler();

        MyinvocationHandler.setTarget(target);

        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),

                MyinvocationHandler);

        return o;

    }

}
