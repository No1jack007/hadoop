package com.zhang.hadoop;

import com.zhang.hadoop.service.HBaseService;
import com.zhang.hadoop.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HadoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopApplication.class, args);
        hBaseTest();

    }

    public static void hBaseTest(){
        try {
            HBaseService hBaseService= SpringUtil.getBean(HBaseService.class);
            hBaseService.insert();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
