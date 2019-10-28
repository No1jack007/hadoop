package com.zhang.hadoop.java_method_strengthen.garnisher;

import com.zhang.hadoop.java_method_strengthen.extend.Animal;
import com.zhang.hadoop.java_method_strengthen.extend.Cat;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 17:02
 **/
public class Test {
    public static void main(String[] args) {
        handleMouse(new SuperCat(new Cat()));
    }
    public static void handleMouse(Animal animal) {
        animal.eat();
    }
}