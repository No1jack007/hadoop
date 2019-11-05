package com.zhang.hadoop.design_mode.decorator;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:35
 **/
public class Decorator implements Sourceable {

    private Sourceable source;

    public Decorator(Sourceable source){
        super();
        this.source = source;
    }
    @Override
    public void method() {
        System.out.println("before decorator!");
        source.method();
        System.out.println("after decorator!");
    }
}
