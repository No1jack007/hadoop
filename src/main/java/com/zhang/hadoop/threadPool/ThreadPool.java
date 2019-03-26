package com.zhang.hadoop.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zhang yufei
 * @create: 2019-03-26 10:09
 **/
public class ThreadPool {

    public static void main(String args[]) throws Exception{
        ExecutorService pool=Executors.newCachedThreadPool();
        for(int i=1;i<10;i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread name:"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(10000);
        System.out.println("-----------");
        for (int i=1;i<15;i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread name:"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();
    }

}
