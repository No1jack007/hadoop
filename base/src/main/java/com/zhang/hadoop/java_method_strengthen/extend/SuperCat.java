package com.zhang.hadoop.java_method_strengthen.extend;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 16:59
 **/
public class SuperCat extends Cat {
    @Override
    public void eat(){
        System.out.println("抓老鼠");
        super.eat();
    }
}
