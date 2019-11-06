package com.zhang.hadoop.design_mode.command;

/**
 * @author: zhang yufei
 * @create: 2019-11-06 10:18
 **/
public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action(){
        command.exe();
    }
}
