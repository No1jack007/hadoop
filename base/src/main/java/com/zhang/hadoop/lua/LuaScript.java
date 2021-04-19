package com.zhang.hadoop.lua;

/**
 * @author ：zhangyufei.zhang
 * @date ：Created in 2021-04-19 15:28
 * @description：
 */
public class LuaScript {

    public static final String YAN_CHI_FU_WU="local quename = KEYS[1]\n" +
            "local start = 0\n" +
            "if KEYS[2] then\n" +
            "\tstart = tonumber(KEYS[2])\n" +
            "end\n" +
            "local limit = 9\n" +
            "if KEYS[3] then\n" +
            "\tlimit = tonumber(KEYS[3])\n" +
            "end\n" +
            "local data=redis.call('zrange',quename,start,limit)\n" +
            "if data then\n" +
            "\tlocal size=#data\n" +
            "\tfor i=1,size do\n" +
            "\t\tredis.call('zrem',quename,data[i])\n" +
            "\tend\n" +
            "end\n" +
            "return data";

}
