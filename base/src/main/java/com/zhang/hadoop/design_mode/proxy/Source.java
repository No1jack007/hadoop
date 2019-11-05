package com.zhang.hadoop.design_mode.proxy;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:50
 **/
public class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
