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
            result=redisService.hmget(Constants.ROW_SEPARATOR+Constants.ROW_SEPARATOR+userId);
            if(result!=null && result.size()>0){
                return  result;
            }
            ResultScanner scanner=hBaseService.rowKeyFilter(Constants.NOT_TABLE_NAME,userId+"*");
            for (Result row : scanner) {
                Map result1=new HashMap<>();
                result1.put("rowKey",row.getRow());
                byte[] value = row.getValue(Bytes.toBytes("info1"), Bytes.toBytes("name"));
                result1.put("value",value);
                byte[] age = row.getValue(Bytes.toBytes("info1"), Bytes.toBytes("age"));
                result1.put("age",age);
                byte[] like = row.getValue(Bytes.toBytes("info2"), Bytes.toBytes("like"));
                result1.put("like",like);
                result.put(result1.get("rowKey"),result1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }
}
