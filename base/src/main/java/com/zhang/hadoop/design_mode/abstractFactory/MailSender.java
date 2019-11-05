package com.zhang.hadoop.design_mode.abstractFactory;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:17
 **/
public class MailSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is mailsender!");
    }
}