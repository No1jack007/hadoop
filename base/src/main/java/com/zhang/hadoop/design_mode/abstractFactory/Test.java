package com.zhang.hadoop.design_mode.abstractFactory;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:19
 **/
public class Test {
    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.Send();
    }
}
