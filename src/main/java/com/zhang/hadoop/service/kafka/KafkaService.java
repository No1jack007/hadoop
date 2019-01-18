package com.zhang.hadoop.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhang yufei on 2018/12/27.
 */
@Service
public class KafkaService {

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private KafkaConsumerService kafkaConsumerService;
    @Autowired
    private KafkaAndStormService kafkaAndStormService;

    public void test(){
//        kafkaProducerService.producer();
//        kafkaConsumerService.consumer();
//        kafkaAndStormService.test();
    }


}
