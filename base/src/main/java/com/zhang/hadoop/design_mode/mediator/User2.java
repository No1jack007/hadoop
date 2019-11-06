package com.zhang.hadoop.design_mode.mediator;

/**
 * @author: zhang yufei
 * @create: 2019-11-06 10:47
 **/
public class User2  extends User {

    public User2(Mediator mediator){
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user2 exe!");
    }
}
