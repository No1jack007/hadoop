package com.zhang.hadoop.design_mode.memento;

/**
 * @author: zhang yufei
 * @create: 2019-11-06 10:21
 **/
public class Memento {

    private String value;

    public Memento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
