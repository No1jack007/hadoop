package com.zhang.hadoop.design_mode.bridge;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:00
 **/
public class MyBridge extends Bridge {
    public void method(){
        getSource().method();
    }
}
