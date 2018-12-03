package com.zhang.hadoop;

import com.zhang.hadoop.service.hbase.HBaseService;
import com.zhang.hadoop.service.hdfs.HdfsClientService;
import com.zhang.hadoop.service.zooKeeper.ZkClientService;
import com.zhang.hadoop.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages={"com.zhang"})
@EnableScheduling
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        hBaseTest();
        zookeeperTest();
    }

    @Bean
    public SpringUtil getSpringUtil2() {
        return new SpringUtil();
    }

    public static void hBaseTest() {
        HBaseService bean= SpringUtil.getBean(HBaseService.class);
        bean.test();
    }

    public static void zookeeperTest() {
        ZkClientService bean= SpringUtil.getBean(ZkClientService.class);
        bean.test();
    }

    public static void hdfsTest() {
        HdfsClientService bean= SpringUtil.getBean(HdfsClientService.class);
        bean.test();
    }

}
