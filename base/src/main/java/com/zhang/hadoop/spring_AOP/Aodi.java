package com.zhang.hadoop.spring_AOP;

/**
 * @author: zhang yufei
 * @create: 2019-10-30 15:57
 **/
public class Aodi implements Car {

    private String name;

    public Aodi(String name) {

        this.name = name;

    }

    public String getCarName() {

// TODO Auto-generated method stub

        System.out.println("car---name");

        return name;

    }

}
