package com.zhang.hadoop.service;

import org.redisson.cache.ExpirableValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author ：zhangyufei.zhang
 * @date ：Created in 2021-06-03 20:07
 * @description：
 */
@Service
public class ExecutorService {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    public void process() {
        taskExecutor.execute(() -> {
            try {
                System.out.println("***************你的异步任务写这里************");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
