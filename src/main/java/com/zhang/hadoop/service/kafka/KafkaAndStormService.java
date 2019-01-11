package com.zhang.hadoop.service.kafka;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by zhang yufei on 2019/1/10.
 */

@Service
public class KafkaAndStormService {

    public void test(){
        TopologyBuilder topologyBuilder=new TopologyBuilder();
        KafkaSpoutConfig.Builder<String, String> kafkaBuilder = KafkaSpoutConfig.builder("hadoop1:2181", "test01");
        //设置kafka属于哪个组
        kafkaBuilder.setGroupId("testgroup1");
        //创建kafkaspoutConfig
        KafkaSpoutConfig<String, String> build = kafkaBuilder.build();
        //通过kafkaspoutConfig获得kafkaspout
        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<String,String>(build);
        //设置5个线程接收数据
        topologyBuilder.setSpout("kafkaSpout",kafkaSpout,1);
//        topologyBuilder.setSpout("kafkaSpout",new KafkaSpout(new SpoutConfig(new ZkHosts("hadoop1:2181"),"orderMq1","/myKafka","kafkaSpout")),1);
        topologyBuilder.setBolt("myboltl",new MyKafkaBolt1(),1).localOrShuffleGrouping("kafkaSpout");
        Config config = new Config();
        config.setNumWorkers(2);
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("stormKafka", config, topologyBuilder.createTopology());
    }
}
