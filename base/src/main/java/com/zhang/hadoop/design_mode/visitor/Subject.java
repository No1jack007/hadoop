package com.zhang.hadoop.design_mode.visitor;

public interface Subject {

    public void accept(Visitor visitor);
    public String getSubject();

}
