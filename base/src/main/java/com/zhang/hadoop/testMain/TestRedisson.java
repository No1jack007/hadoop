package com.zhang.hadoop.testMain;

import com.zhang.hadoop.lua.LuaScript;
import com.zhang.hadoop.util.RedissonUtil;
import org.junit.Test;
import org.redisson.api.RList;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author ：zhangyufei.zhang
 * @date ：Created in 2021-04-19 15:30
 * @description：
 */
public class TestRedisson {

    private RedissonClient redissonClient = RedissonUtil.getRedissonClientInstance4Delayservice();

    @Test
    public void test01() {
        String key = "test-zset";
        String start = "0";
        String limit = "3";
        List<Object> result=redissonClient.getScript().eval(RScript.Mode.READ_WRITE, LuaScript.YAN_CHI_FU_WU, RScript.ReturnType.MULTI, Arrays.asList(key,start,limit));
        for(Object s:result){
            System.out.println(s);
        }
    }

    @Test
    public void test02() {
        String key = "test-zset";
        String data="1";
        RList<String> result=redissonClient.getList(key);
        if (result.isEmpty()) {
            result.add(data);
        } else if (result.size() < 15) {
            result.add(data);
        }
    }
}
