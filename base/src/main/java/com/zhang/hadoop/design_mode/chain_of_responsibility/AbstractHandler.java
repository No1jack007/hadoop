package com.zhang.hadoop.design_mode.chain_of_responsibility;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 19:40
 **/
public abstract class AbstractHandler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}
