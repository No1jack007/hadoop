package com.zhang.hadoop.design_mode.strategy;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:10
 **/
public class Multiply extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp,"\\*");
        return arrayInt[0]*arrayInt[1];
    }
}
