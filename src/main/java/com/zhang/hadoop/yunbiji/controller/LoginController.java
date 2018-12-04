package com.zhang.hadoop.yunbiji.controller;

import com.zhang.hadoop.util.StringUtil;
import com.zhang.hadoop.yunbiji.constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhang yufei on 2018/12/3.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request,String userId){
        if(StringUtil.isEmpty(userId)){
            return "error";
        }
        request.getSession().setAttribute(Constants.USER_INFO,userId);
        return "success";
    }
}
