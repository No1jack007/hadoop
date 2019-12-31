package com.zhang.spring_cloud_nacos_consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhang yufei
 * @create: 2019-12-24 17:59
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/greeting")
    public String greeting() {
        return "Hello,World!";
    }

    @GetMapping("/login")
    public String login() {

        return "login sucess";
    }
}
