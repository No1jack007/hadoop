package com.zhang.hadoop.java_method_strengthen.garnisher;

import com.zhang.hadoop.java_method_strengthen.extend.Animal;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 17:02
 **/
public class SuperCat implements Animal {
    Animal animal;
    public SuperCat(Animal animal) {
        this.animal = animal;
    }
    @Override
    public void eat() {
        System.out.println("抓老鼠");
        animal.eat();
    }
}
