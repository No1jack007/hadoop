package com.zhang.hadoop.design_mode.adapter.object_adapter;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:24
 **/
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source){
        super();
        this.source = source;
    }
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

    @Override
    public void method1() {
        source.method1();
    }
}
