package com.zhang.hadoop.util;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author liuhongming
 */
public class RedissonUtil {

    private static Logger logger = LoggerFactory.getLogger(RedissonUtil.class);

    private static final Object locker = new Object();
    private static volatile RedissonClient instance = null;

    /**
     * 单例的 redisson client 对象，防止创建太多redis连接
     */
    public static RedissonClient getRedissonClientInstance4Delayservice() {
        return getRedissonClientInstance("redisson-delayedservice.yml");
    }

    public static RedissonClient getRedissonClientInstance(String redissonConfig) {
        if (instance == null) {
            synchronized (locker) {
                if (instance == null) {
                    try {
                        ClassPathResource classPathResource = new ClassPathResource(redissonConfig);
                        if (classPathResource.exists()) {
                            instance = Redisson.create(
                                Config.fromYAML(classPathResource.getInputStream()));
                        }
                    } catch (IOException ex) {
                        logger.error("RedissonClient 初始化异常 ...", ex);
                    }
                }
            }
        }
        return instance;
    }

}
