package com.zhang.hadoop.design_mode.bridge;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:59
 **/
public class SourceSub2 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the second sub!");
    }
}
