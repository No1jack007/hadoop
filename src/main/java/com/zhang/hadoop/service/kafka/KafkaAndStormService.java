package com.zhang.hadoop.service.kafka;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by zhang yufei on 2019/1/10.
 */

@Service
public class KafkaAndStormService {

    public void test(){
        TopologyBuilder topologyBuilder=new TopologyBuilder();
        topologyBuilder.setSpout("kafkaSpout",new KafkaSpout(new SpoutConfig(new ZkHosts("hadoop1:2181"),"orderMq","/myKafka","kafkaSpout")),1);
        topologyBuilder.setBolt("myboltl",new MyKafkaBolt1(),1).shuffleGrouping("kafkaSpout");
        Config config = new Config();
        config.setNumWorkers(2);
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("myWordCount", config, topologyBuilder.createTopology());
    }
}
