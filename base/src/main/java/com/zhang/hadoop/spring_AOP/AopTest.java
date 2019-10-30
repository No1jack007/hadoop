package com.zhang.hadoop.spring_AOP;

/**
 * @author: zhang yufei
 * @create: 2019-10-30 15:58
 **/
public class AopTest {
    public static void main(String[] args) {

        Car car = new Aodi("222");

        Car proxyCar = (Car) MyProxyFactory.getObject(car);

        proxyCar.getCarName();

    }

}
