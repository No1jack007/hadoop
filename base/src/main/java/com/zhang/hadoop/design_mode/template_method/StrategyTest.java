package com.zhang.hadoop.design_mode.template_method;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:20
 **/
public class StrategyTest {

    public static void main(String[] args) {
        String exp = "8+8";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }

}
