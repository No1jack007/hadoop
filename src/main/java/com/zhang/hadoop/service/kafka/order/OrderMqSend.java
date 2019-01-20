package com.zhang.hadoop.service.kafka.order;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
import java.util.UUID;

/**
 * Created by zhang yufei on 2019/1/20.
 */
public class OrderMqSend {

    public static void main(String args[]){
        String TOPIC="orderMq1";
        Properties properties=new Properties();
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("metadata.broker.list","hadoop1:9092");
        properties.put("request.required.acks", "1");
//        properties.put("partitioner.class","com.zhang.hadoop.service.kafka.MyLogPartitioner");
        Producer<String,String> producer=new Producer<String, String>(new ProducerConfig(properties));
        for(int messageNo=1;messageNo<100;messageNo++){
            String messageStr=new String (messageNo+":zhang yufei");
            producer.send(new KeyedMessage<String,String>(TOPIC,messageNo+"","appid:"+ UUID.randomUUID()+":"+messageStr));
        }
        System.out.println("数据生产完成");
        producer.close();
    }
}
