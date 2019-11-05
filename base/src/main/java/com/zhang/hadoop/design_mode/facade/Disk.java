package com.zhang.hadoop.design_mode.facade;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:56
 **/
public class Disk {
    public void startup(){
        System.out.println("disk startup!");
    }

    public void shutdown(){
        System.out.println("disk shutdown!");
    }
}
