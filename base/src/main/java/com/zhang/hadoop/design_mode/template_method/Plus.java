package com.zhang.hadoop.design_mode.template_method;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:20
 **/
public class Plus extends AbstractCalculator {

    @Override
    public int calculate(int num1,int num2) {
        return num1 + num2;
    }
}
