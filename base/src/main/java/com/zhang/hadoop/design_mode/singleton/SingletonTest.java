package com.zhang.hadoop.design_mode.singleton;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:21
 **/
public class SingletonTest {
    private static SingletonTest instance = null;

    private SingletonTest() {
    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new SingletonTest();
        }
    }

    public static SingletonTest getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }
}
