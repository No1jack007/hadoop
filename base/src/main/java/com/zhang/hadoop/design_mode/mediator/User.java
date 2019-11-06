package com.zhang.hadoop.design_mode.mediator;

/**
 * @author: zhang yufei
 * @create: 2019-11-06 10:46
 **/
public abstract  class User {

    private Mediator mediator;

    public Mediator getMediator(){
        return mediator;
    }

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void work();
}
