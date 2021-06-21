package com.zhang.hadoop;

import com.zhang.hadoop.redis.RedisService;
import com.zhang.hadoop.service.ExecutorService;
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
        hdfsTest();
        stormTest();
        kafkaTest();
        hbaseUtilTest();
        redisTest();
        executorTest();
    }

    @Bean
    public SpringUtil getSpringUtil2() {
        return new SpringUtil();
    }

    public static void hBaseTest() {
//        HBaseService bean= SpringUtil.getBean(HBaseService.class);
//        bean.test();
    }

    public static void zookeeperTest() {
//        ZkClientService bean= SpringUtil.getBean(ZkClientService.class);
//        bean.test();
    }

    public static void hdfsTest() {
//        HdfsClientService bean= SpringUtil.getBean(HdfsClientService.class);
//        bean.test();
    }

    public static void stormTest(){
//        WordCountMain bean=SpringUtil.getBean(WordCountMain.class);
//        bean.test();
    }

    public static void kafkaTest(){
//        KafkaService bean=SpringUtil.getBean(KafkaService.class);
//        bean.test();
    }

    public static void hbaseUtilTest(){
//        ModuleService bean=SpringUtil.getBean(ModuleService.class);
//        Map<String,Object> map=new HashMap<>();
//        map.put("code","5");
//        map.put("cell","zhang zhang zhang");
//        bean.insertOneModule(map);
    }

    public static void redisTest() {
//        RedisService bean= SpringUtil.getBean(RedisService.class);
//        bean.set("b","1");
    }

    public static void executorTest(){
        ExecutorService bean=SpringUtil.getBean(ExecutorService.class);
        bean.process();
    }

}
