package com.zhang.hadoop.service.batteryService.service;

import com.zhang.hadoop.util.HBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhang yufei
 * @create: 2019-03-14 09:14
 **/
@Service
public class ModuleService {

    @Autowired
    private HBaseUtil hBaseUtil;

    public Map<String,Object> insertOneModule(Map<String,Object> data){
        Map<String,Object> result=new HashMap();
        try {
            hBaseUtil.insertOneRecord("test_table",data.get("code").toString(),"family1","cell",data.get("cell").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
