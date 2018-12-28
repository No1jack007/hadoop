package com.zhang.hadoop.service.kafka;


import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Created by zhang yufei on 2018/12/28.
 */
@Service
public class KafkaProducerService {

    public void init(){
        String TOPIC="test01";
        Properties properties=new Properties();
        properties.put("serializer.class","kafka.serializer.StringEncoder");
        properties.put("metadata.broker.list","hadoop1:9092");
        /*
        * 0 最低延迟，持久性最弱，只发出不管replica是否持久完成
        * 1 速度和持久性适中，leader replica只要有一个接收到数据后，producer会得到一个ack
        * -1 速度最慢，持久性最好，写入到所有replica后，再返回一个ack，只要有一个replica存在，数据就不会丢失
        * */
        properties.put("request.required.acks","1");
        properties.put("partitioner.class","cn.itcast.hadoop.kafka.MyLogPartitioner");

        Producer<String,String> producer=new Producer<String, String>(new ProducerConfig(properties));

    }
}
