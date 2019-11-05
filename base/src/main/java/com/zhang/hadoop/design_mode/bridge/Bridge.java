package com.zhang.hadoop.design_mode.bridge;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:59
 **/
public abstract class Bridge {

    private Sourceable source;

    public void method(){
        source.method();
    }

    public Sourceable getSource() {
        return source;
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }

}
