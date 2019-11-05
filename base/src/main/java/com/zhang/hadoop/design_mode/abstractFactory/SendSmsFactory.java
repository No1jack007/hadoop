package com.zhang.hadoop.design_mode.abstractFactory;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:19
 **/
public class SendSmsFactory implements Provider{

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
