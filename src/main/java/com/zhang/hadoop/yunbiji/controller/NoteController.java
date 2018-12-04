package com.zhang.hadoop.yunbiji.controller;

import com.zhang.hadoop.util.StringUtil;
import com.zhang.hadoop.yunbiji.constants.Constants;
import com.zhang.hadoop.yunbiji.service.NoteService;
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

}
