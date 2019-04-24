package com.zhang.hadoop.spark.yunbiji.controller;

import com.zhang.hadoop.spark.yunbiji.constants.Constants;
import com.zhang.hadoop.spark.yunbiji.service.NoteService;
import com.zhang.hadoop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang yufei on 2018/12/3.
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping("/getAllNoteBook")
    @ResponseBody
    public Map<String,Object> getAllNoteBook(HttpServletRequest request){
        Map<String,Object> result=new HashMap<>();
        String userId=request.getSession().getAttribute(Constants.USER_INFO).toString();
        if(StringUtil.isEmpty(userId)){
            result.put("message","error");
        }
        result=noteService.getAllNoteBook(userId);
        return result;
    }

    @RequestMapping("/addNoteBook")
    @ResponseBody
    public Map<String,Object> addNoteBook(HttpServletRequest request,String noteBookName){
        Map<String,Object> result=new HashMap<>();
        String userId=request.getSession().getAttribute(Constants.USER_INFO).toString();
        if(StringUtil.isEmpty(userId)){
            result.put("message","error");
        }
        Long creteTime=System.currentTimeMillis();
        result=noteService.addNoteBook(userId,creteTime.toString(),noteBookName);
        return result;
    }

    @RequestMapping("/deleteNoteBook")
    @ResponseBody
    public Map<String,Object> deleteNoteBook(HttpServletRequest request,String rowKey,String noteBookName){
        Map<String,Object> result=new HashMap<>();
        String userId=request.getSession().getAttribute(Constants.USER_INFO).toString();
        if(StringUtil.isEmpty(userId)){
            result.put("message","error");
        }
        String split[]= rowKey.split("\\"+Constants.ROW_SEPARATOR);
        result=noteService.deleteNoteBook(split[0],split[1],noteBookName);
        return result;
    }

}
