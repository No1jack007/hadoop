package com.zhang.hadoop.yunbiji.service;

import com.zhang.hadoop.service.hbase.HBaseService;
import com.zhang.hadoop.service.redis.RedisService;
import com.zhang.hadoop.yunbiji.constants.Constants;
import io.lettuce.core.cluster.pubsub.api.sync.PubSubNodeSelection;
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
        Map result = new HashMap<>();
        try {
            result = redisService.hmget(Constants.USER_INFO + Constants.ROW_SEPARATOR + userId);
            if (result != null && result.size() > 0) {
                return result;
            }
            result = hBaseService.rowKeyFilter(Constants.NOT_TABLE_NAME, userId + "*");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> addNoteBook(String userId, String creteTime, String noteBookName) {
        Map result = new HashMap<>();
        try {
            boolean isSuccess = false;
            isSuccess = this.addNoteBookToRedis(userId, creteTime, noteBookName);
            if (isSuccess) {
                isSuccess = addNoteBookToHBase(userId, creteTime, noteBookName);
                if (!isSuccess) {
                    this.deleteNoteBookFromRedis(userId, creteTime, noteBookName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.deleteNoteBookFromRedis(userId, creteTime, noteBookName);
        }
        return result;
    }

    public boolean addNoteBookToRedis(String userI, String createTime, String noteBookName) {

        return true;
    }

    public boolean addNoteBookToHBase(String userI, String createTime, String noteBookName) {

        return true;
    }

    public boolean deleteNoteBookFromRedis(String userI, String createTime, String noteBookName) {

        return true;
    }

}
