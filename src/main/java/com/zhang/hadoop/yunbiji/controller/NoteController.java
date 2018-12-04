package com.zhang.hadoop.yunbiji.controller;

import com.zhang.hadoop.yunbiji.constants.Constants;
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

    @RequestMapping("/getAllNoteBook")
    @ResponseBody
    public Map<String,Object> getAllNoteBook(HttpServletRequest request){
        Map<String,Object> result=new HashMap<>();
        String userId=request.getSession().getAttribute(Constants.USER_INFO).toString();
        return result;
    }
}
