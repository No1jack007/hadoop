package com.zhang.hadoop.design_mode.strategy;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:09
 **/
public abstract class AbstractCalculator {

    public int[] split(String exp,String opt){
        String array[] = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }

}
