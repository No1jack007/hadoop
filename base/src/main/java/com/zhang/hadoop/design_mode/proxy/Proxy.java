package com.zhang.hadoop.design_mode.proxy;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:51
 **/
public class Proxy  implements Sourceable {

    private Source source;
    public Proxy(){
        super();
        this.source = new Source();
    }
    @Override
    public void method() {
        before();
        source.method();
        atfer();
    }
    private void atfer() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }
}
