package com.zhang.hadoop.kafka;


import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhang yufei on 2018/12/28.
 */
@Service
public class KafkaConsumerService implements Runnable{

    private String title;
    private KafkaStream<byte[],byte[]> stream;

    public KafkaConsumerService(){}

    public KafkaConsumerService(String title,KafkaStream<byte[],byte[]> stream){
        this.title=title;
        this.stream=stream;
    }

    @Override
    public void run() {
        System.out.println("开始运行："+title);
        ConsumerIterator<byte[],byte[]> it=stream.iterator();
        while (it.hasNext()){
            MessageAndMetadata<byte[],byte[]> data=it.next();
            String topic=data.topic();
            int partition=data.partition();
            long offset=data.offset();
            String msg=new String(data.message());
            System.out.println((String.format("Consumer:[%s],Topic:[%s],PartitionId:[%d],Offset:[%d],msg[%s]",title,topic,partition,offset,msg)));
        }
    }

    public void consumer() {
        Properties properties = new Properties();
        properties.put("group.id", "testGroup");
        properties.put("zookeeper.connect", "hadoop1:2181");
        properties.put("auto.offset.reset", "largest");
        properties.put("partition.assignment.strategy", "roundrobin");
        ConsumerConfig consumerConfig = new ConsumerConfig(properties);
        String topic = "test01";
        ConsumerConnector consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);
        Map<String,Integer> topicCountMap=new HashMap<>();
        topicCountMap.put(topic,1);
        Map<String, List<KafkaStream<byte[],byte[]>>> topicStreamsMap=consumerConnector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[],byte[]>> streams=topicStreamsMap.get(topic);
        ExecutorService executorService= Executors.newFixedThreadPool(5);
        for(int i=0;i<streams.size();i++){
            executorService.execute(new KafkaConsumerService("消费者"+(i+1),streams.get(i)));
        }
    }
}
