package com.zhang.hadoop.design_mode.visitor;

/**
 * @author: zhang yufei
 * @create: 2019-11-06 10:41
 **/
public class MyVisitor implements Visitor {

    @Override
    public void visit(Subject sub) {
        System.out.println("visit the subject："+sub.getSubject());
    }
}
