package com.zhang.hadoop.spring_AOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: zhang yufei
 * @create: 2019-10-30 15:53
 **/
public class MyinvocationHandler implements InvocationHandler {

    private Object target;

    /**
     * @param o
     */

    public void setTarget(Object o) {

        this.target = o;

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // TODO Auto-generated method stub

        Extend.beforeExtend();

        Object result = method.invoke(target, args);

        Extend.afterExtend();

        return result;

    }


}
