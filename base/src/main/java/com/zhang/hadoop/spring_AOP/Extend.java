package com.zhang.hadoop.spring_AOP;

/**
 * @author: zhang yufei
 * @create: 2019-10-30 15:54
 **/
public class Extend {
    public static void beforeExtend() {

        System.out.println("前置增强");

    }

    public static void afterExtend() {

        System.out.println("后置增强");

    }

}
