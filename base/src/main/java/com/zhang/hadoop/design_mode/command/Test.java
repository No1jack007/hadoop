package com.zhang.hadoop.design_mode.command;

/**
 * @author: zhang yufei
 * @create: 2019-11-06 10:19
 **/
public class Test {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command cmd = new MyCommand(receiver);
        Invoker invoker = new Invoker(cmd);
        invoker.action();
    }
}
