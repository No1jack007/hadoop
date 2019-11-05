package com.zhang.hadoop.design_mode.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:44
 **/
public abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<Observer>();
    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();
        while(enumo.hasMoreElements()){
            enumo.nextElement().update();
        }
    }
}
