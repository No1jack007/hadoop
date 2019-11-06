package com.zhang.hadoop.design_mode.command;

/**
 * @author: zhang yufei
 * @create: 2019-11-06 10:18
 **/
public class MyCommand  implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exe() {
        receiver.action();
    }
}
