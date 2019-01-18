package com.zhang.hadoop.service.kafka;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import org.springframework.stereotype.Service;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;
import storm.kafka.bolt.KafkaBolt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang yufei on 2019/1/10.
 */

@Service
public class KafkaAndStormService {

    public static void main(String[] args) {

//        BrokerHosts brokerHosts = new ZkHosts("hadoop1:2181", "/brokers");
//        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "test01", "/kafka", "word-count-topology");
//        Config conf = new Config();
//        Properties properties = new Properties();
//        properties.put("bootstrap.servers", "hadoop1:9092");
//        properties.put("serializer.class", "kafka.serializer.StringEncoder");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        conf.put("kafka.broker.properties", properties);
//        conf.put("bootstrap.servers", "hadoop1:9092");
//        conf.put("topic", "test01");
//        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
//        TopologyBuilder builder = new TopologyBuilder();
//        builder.setSpout("testGroup", new KafkaSpout(spoutConfig));
//        builder.setBolt("myboltl", new MyKafkaBolt1()).shuffleGrouping("testGroup");
////        KafkaBolt kafkaBolt = new KafkaBolt();
////        kafkaBolt.withProducerProperties(properties);
////        builder.setBolt("msgKafkaBolt", kafkaBolt).shuffleGrouping(SPLIT_BOLT_ID);
//        Config config = new Config();
//        config.setDebug(true);
//        config.setNumWorkers(2);
//        LocalCluster localCluster = new LocalCluster();
//        localCluster.submitTopology("stormKafka", config, builder.createTopology());
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        TopologyBuilder builder = new TopologyBuilder();

//        builder.setSpout("testGroup", new KafkaSpout("test01"));
//        builder.setBolt("myboltl", new MyKafkaBolt1()).shuffleGrouping("testGroup");
//        Config config = new Config();
//        config.setDebug(true);
//        if (args != null && args.length > 0) {
//            // online commit Topology
//            config.put(Config.NIMBUS_HOST, args[0]);
//            config.setNumWorkers(3);
//            try {
////                StormSubmitter.submitTopologyWithProgressBar(KafkaTopology.class.getSimpleName(), config,builder.createTopology());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            // Local commit jar
//            LocalCluster local = new LocalCluster();
//            local.submitTopology("counter", config, builder.createTopology());
//            try {
//                Thread.sleep(60000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////            local.shutdown();
//        }


//        TopologyBuilder topologyBuilder = new TopologyBuilder();
//
////        topologyBuilder.setSpout("kafkaSpout", new KafkaSpout(new SpoutConfig(new ZkHosts("hadoop1:2181"), "test01", "/myKafka", "kafkaSpout")), 1);
//
//        KafkaSpoutConfig.Builder<String, String> kafkaBuilder = KafkaSpoutConfig.builder("192.168.10.11:2181", "test01");
////        设置kafka属于哪个组
//        kafkaBuilder.setGroupId("testgroup2");
////        创建kafkaspoutConfig
//        KafkaSpoutConfig<String, String> build = kafkaBuilder.build();
////        通过kafkaspoutConfig获得kafkaspout
//        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<String, String>(build);
////        设置5个线程接收数据
//        topologyBuilder.setSpout("kafkaSpout", kafkaSpout, 1);
//
////        BrokerHosts brokerHosts = new ZkHosts("hadoop1:2181");
////        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "test01", "/brokerHosts", "kafkaspout");
////        Config conf = new Config();
////        Map<String, String> map = new HashMap<String, String>();
////        map.put("metadata.broker.list", "hadoop1:9092");
////        map.put("serializer.class", "kafka.serializer.StringEncoder");
////        conf.put("kafka.broker.properties", map);
////        conf.put("topic", "test01");
////        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
////        topologyBuilder.setSpout("kafkaSpout", spoutConfig);
//
//        topologyBuilder.setBolt("myboltl", new MyKafkaBolt1(), 1).localOrShuffleGrouping("kafkaSpout");
//
//        Config config = new Config();
//        config.setDebug(false);
//        config.setNumWorkers(2);
//        LocalCluster localCluster = new LocalCluster();
//        localCluster.submitTopology("stormKafka", config, topologyBuilder.createTopology());
//        try {
//            Thread.sleep(60000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        localCluster.killTopology("stormKafka");
//        localCluster.shutdown();

        BrokerHosts brokerHosts = new ZkHosts("192.168.10.11:2181/brokers");

        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "test01", "/brokers/topics", "kafkaspout");

        Config conf = new Config();
        Map<String, String> map = new HashMap<String, String>();

        map.put("metadata.broker.list", "192.168.10.11:9092");
        map.put("serializer.class", "kafka.serializer.StringEncoder");
        conf.put("kafka.broker.properties", map);
        conf.put("topic", "test01");

//        spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new KafkaSpout(spoutConfig));
        builder.setBolt("bolt", new MyKafkaBolt1()).shuffleGrouping("spout");
        builder.setBolt("kafkabolt", new KafkaBolt<String, Integer>()).shuffleGrouping("bolt");

        if (args != null && args.length > 0) {
            //提交到集群运行
//            try {
//                StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
//            } catch (AlreadyAliveException e) {
//                e.printStackTrace();
//            } catch (InvalidTopologyException e) {
//                e.printStackTrace();
//            }
        } else {
            //本地模式运行
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("Topotest1121", conf, builder.createTopology());
            Utils.sleep(1000000);
            cluster.killTopology("Topotest1121");
            cluster.shutdown();
        }

    }
}
