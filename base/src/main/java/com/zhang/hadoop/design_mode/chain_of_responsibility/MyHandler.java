package com.zhang.hadoop.design_mode.chain_of_responsibility;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 19:40
 **/
public class MyHandler extends AbstractHandler implements Handler {

    private String name;

    public MyHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        System.out.println(name+"deal!");
        if(getHandler()!=null){
            getHandler().operator();
        }
    }
}