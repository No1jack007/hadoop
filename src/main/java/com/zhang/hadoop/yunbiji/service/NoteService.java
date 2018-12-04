package com.zhang.hadoop.yunbiji.service;

import com.zhang.hadoop.service.hbase.HBaseService;
import com.zhang.hadoop.service.redis.RedisService;
import com.zhang.hadoop.yunbiji.constants.Constants;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang yufei on 2018/12/3.
 */
@Service
public class NoteService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private HBaseService hBaseService;

    public Map getAllNoteBook(String userId) {
        Map result=new HashMap<>();
        try {
            result=redisService.hmget(Constants.USER_INFO+Constants.ROW_SEPARATOR+userId);
            if(result!=null && result.size()>0){
                return  result;
            }
            result =hBaseService.rowKeyFilter(Constants.NOT_TABLE_NAME,userId+"*");

        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }
}
