package com.zhang.hadoop.java_method_strengthen.extend;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 16:58
 **/
public class Test {

    public static void main(String[] args) {
        handleMouse(new SuperCat());
    }
    public static void handleMouse(Animal animal) {
        animal.eat();
    }

}
