package com.zhang.hadoop.service.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenp on 2017-07-17.
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    
    public Map<Object, Object> hmget(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    
    public Object hget(String key, String field) {
        return stringRedisTemplate.opsForHash().get(key, field);
    }

    
    public void hset(String key, String field, Object value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    
    public void hdel(String key, String field) {
        stringRedisTemplate.opsForHash().delete(key, field);
    }

    
    public void hset(String key, Map<String, Object> values) {
        stringRedisTemplate.opsForHash().putAll(key, values);
    }

    
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    
    public void sadd(String key, String... value) {
        stringRedisTemplate.opsForSet().add(key, value);
    }

    /**
     * 移除集合元素
     *
     * @param key
     * @param value
     */
    
    public void sdel(String key, String... value) {
        stringRedisTemplate.opsForSet().remove(key, value);
    }

    
    public boolean isMember(String key, String value) {
        return stringRedisTemplate.opsForSet().isMember(key, value);
    }


    
    public Set<String> smembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * 删除key
     *
     * @param key
     */
    
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    public boolean expire(String key, int timeout) {
        return stringRedisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 检查key是否存在，返回boolean值
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 设置有时效redis
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean valueFailure(String key,String value,Integer time) {
        stringRedisTemplate.opsForValue().set(key, value);
        return stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }
}
