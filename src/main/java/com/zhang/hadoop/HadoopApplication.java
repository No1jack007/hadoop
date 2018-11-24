package com.zhang.hadoop;

import com.zhang.hadoop.service.HBaseService;
import com.zhang.hadoop.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.zhang"})
public class HadoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopApplication.class, args);
        hBaseTest();

    }

    @Bean
    public SpringUtil getSpringUtil2() {
        return new SpringUtil();
    }

    public static void hBaseTest() {
        try {
            HBaseService hBaseService= SpringUtil.getBean(HBaseService.class);
//            hBaseService.insert();
            hBaseService.deleteDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
