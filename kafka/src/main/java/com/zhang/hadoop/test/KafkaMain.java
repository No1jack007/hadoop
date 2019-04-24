package com.zhang.hadoop.test;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
import java.util.UUID;

/**
 * Created by zhang yufei on 2018/12/29.
 */
public class KafkaMain {

    public static void main(String[] args) {
        producerData();
    }

    private static void producerData() {
        String TOPIC="orderMq1";
        Properties properties=new Properties();
        properties.put("metadata.broker.list","hadoop1:9092");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        /*
         * 0 最低延迟，持久性最弱，只发出不管replica是否持久完成
         * 1 速度和持久性适中，leader replica只要有一个接收到数据后，producer会得到一个ack
         * -1 速度最慢，持久性最好，写入到所有replica后，再返回一个ack，只要有一个replica存在，数据就不会丢失
         * */
        properties.put("request.required.acks", "1");
        properties.put("partitioner.class","com.zhang.hadoop.com.zhang.hadoop.service.kafka.MyLogPartitioner");

        Producer<String,String> producer=new Producer<String, String>(new ProducerConfig(properties));

        for(int messageNo=1;messageNo<100;messageNo++){
            String messageStr=new String (messageNo+":zhang yufei");
            producer.send(new KeyedMessage<String,String>(TOPIC,messageNo+"","appid:"+ UUID.randomUUID()+":"+messageStr));
        }
        producer.close();
//        KafkaAndStormService kafkaAndStormService=new KafkaAndStormService();
//        kafkaAndStormService.test();
    }

}
