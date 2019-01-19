package com.zhang.hadoop.testMain;

import com.zhang.hadoop.service.kafka.KafkaAndStormService;
import com.zhang.hadoop.service.storm.stormWordCount.WordCountMain;

/**
 * Created by zhang yufei on 2019/1/17.
 */
public class TestMain {

    public static void main(String[] args) {
        WordCountMain wordCountMain=new WordCountMain();
//        wordCountMain.test();
        KafkaAndStormService kafkaAndStormService=new KafkaAndStormService();
        kafkaAndStormService.test(args);
    }
}
