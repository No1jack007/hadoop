package com.zhang.hadoop.design_mode.visitor;

/**
 * @author: zhang yufei
 * @create: 2019-11-06 10:42
 **/
public class MySubject  implements Subject {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }
}
