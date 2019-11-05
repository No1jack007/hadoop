package com.zhang.hadoop.design_mode.observer;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:43
 **/
public class Observer1  implements Observer {

    @Override
    public void update() {
        System.out.println("observer1 has received!");
    }
}
