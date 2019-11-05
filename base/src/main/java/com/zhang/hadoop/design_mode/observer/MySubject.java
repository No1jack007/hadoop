package com.zhang.hadoop.design_mode.observer;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:44
 **/
public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }

}

