package com.zhang.hadoop.design_mode.abstractFactory;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:18
 **/
public class SendMailFactory implements Provider {

    @Override
    public Sender produce(){
        return new MailSender();
    }
}