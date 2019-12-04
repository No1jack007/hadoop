package com.zhang.hadoop.service.yunbiji.service;

import com.zhang.hadoop.spark.hbase.HBaseService;
import com.zhang.hadoop.redis.RedisService;
import com.zhang.hadoop.spark.yunbiji.constants.Constants;
import jodd.json.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            Map<String, List<String>> map = new HashMap<>();
            List<String> info1 = new ArrayList<>();
            info1.add("name");
            info1.add("age");
            map.put("info1", info1);
            List<String> info2 = new ArrayList<>();
            info2.add("like");
            map.put("info2", info2);
            result = hBaseService.rowKeyFilter(Constants.NOT_TABLE_NAME, userId + "", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> addNoteBook(String userId, String createTime, String noteBookName) {
        Map result = new HashMap<>();
        try {
            boolean isSuccess = false;
            isSuccess = this.addNoteBookToRedis(userId, createTime, noteBookName);
            if (isSuccess) {
                isSuccess = addNoteBookToHBase(userId, createTime, noteBookName);
                if (!isSuccess) {
                    this.deleteNoteBookFromRedis(userId, createTime, noteBookName);
                }
            }
            String rowKey = userId + Constants.ROW_SEPARATOR + createTime;
            result.put("rowKey", rowKey);
            result.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            this.deleteNoteBookFromRedis(userId, createTime, noteBookName);
            result.put("status", "error");
        }
        return result;
    }

    public boolean addNoteBookToRedis(String userId, String createTime, String noteBookName) {
        StringBuffer noteBookToSting = new StringBuffer();
        noteBookToSting.append(userId).append(Constants.ROW_SEPARATOR)
                .append(createTime).append(Constants.STRING_SEPARATOR)
                .append(noteBookName).append(Constants.STRING_SEPARATOR)
                .append(createTime).append(Constants.STRING_SEPARATOR);
        redisService.set(noteBookToSting.toString(), noteBookName);
        return true;
    }

    public boolean addNoteBookToHBase(String userId, String createTime, String noteBookName) throws Exception {
        String rowKey = userId + Constants.ROW_SEPARATOR + createTime;
        List<String> noteList = new ArrayList<>();
        String noteListToJson = new JsonSerializer().deep(true).serialize(noteList);
        Map data = new HashMap();
        Map column1 = new HashMap();
        column1.put("name", noteBookName);
        column1.put("createTime", createTime);
        column1.put("noteBookInfo", noteListToJson);
        data.put("info1", column1);
        hBaseService.insert(Constants.NOT_TABLE_NAME, rowKey, data);
        return true;
    }

    public boolean deleteNoteBookFromRedis(String userId, String createTime, String noteBookName) {
        StringBuffer noteBookToSting = new StringBuffer();
        noteBookToSting.append(userId).append(Constants.ROW_SEPARATOR)
                .append(createTime).append(Constants.STRING_SEPARATOR)
                .append(noteBookName).append(Constants.STRING_SEPARATOR)
                .append(createTime).append(Constants.STRING_SEPARATOR);
        redisService.del(noteBookToSting.toString());
        return true;
    }

    public Map<String, Object> deleteNoteBook(String userId, String createTime, String noteBookName) {
        Map result = new HashMap<>();
        try {
            boolean isSuccess = false;
            isSuccess = this.deleteNoteBookFromRedis(userId, createTime, noteBookName);
            if (isSuccess) {
                isSuccess = deleteNoteBookFromHBase(userId, createTime, noteBookName);
                if (isSuccess) {
                    this.addNoteBookToRedis(userId, createTime, noteBookName);
                }
            }
            result.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            this.addNoteBookToRedis(userId, createTime, noteBookName);
            result.put("status", "error");
        }
        return result;
    }

    public boolean deleteNoteBookFromHBase(String userId, String createTime, String noteBookName) throws Exception {
        String rowKey = userId + Constants.ROW_SEPARATOR + createTime;
        hBaseService.delete(Constants.NOT_TABLE_NAME, rowKey);
        return true;
    }

}