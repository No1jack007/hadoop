package com.zhang.hadoop.design_mode.facade;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:55
 **/
public class CPU {
    public void startup(){
        System.out.println("cpu startup!");
    }

    public void shutdown(){
        System.out.println("cpu shutdown!");
    }
}
