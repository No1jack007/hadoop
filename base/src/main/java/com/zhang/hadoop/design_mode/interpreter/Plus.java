package com.zhang.hadoop.design_mode.interpreter;

/**
 * @author: zhang yufei
 * @create: 2019-11-06 10:49
 **/
public class Plus implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getNum1()+context.getNum2();
    }
}
