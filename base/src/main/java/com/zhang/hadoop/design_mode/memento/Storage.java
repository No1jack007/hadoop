package com.zhang.hadoop.design_mode.memento;

/**
 * @author: zhang yufei
 * @create: 2019-11-06 10:21
 **/
public class Storage {

    private Memento memento;

    public Storage(Memento memento) {
        this.memento = memento;
    }

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

}
