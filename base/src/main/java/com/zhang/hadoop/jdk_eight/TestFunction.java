package com.zhang.hadoop.jdk_eight;

/**
 * @author: zhang yufei
 * @create: 2019-10-25 16:17
 **/
public class TestFunction {

    public static void main(String args[]){
        Hello hello = param ->param + "world!";
        System.out.println("test functional:" + hello.msg("hello,"));
    }

}
