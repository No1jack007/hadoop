package com.zhang.hadoop.storm.sotrmKafka;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang yufei on 2019/1/10.
 */

@Service
public class KafkaAndStormService {

    public static void test(String[] args) {


        String zkConnString = "hadoop1:2181";
        BrokerHosts hosts = new ZkHosts(zkConnString);
        SpoutConfig spoutConfig = new SpoutConfig(hosts,"test01","/brokers/topics", "spout1");
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        // 记录Spout读取进度所用的zookeeper的host,即记录offset位置的zk
        List<String> servers  = new ArrayList<>();
        servers.add("hadoop1");
        spoutConfig.zkServers = servers;
        spoutConfig.zkPort = 2181;
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaspout",kafkaSpout);
        builder.setBolt("myboltl",new MyKafkaBolt1()).shuffleGrouping("kafkaspout");
        Config config = new Config();
        config.setDebug(true);
        if (args != null && args.length > 0) {
//            提交到集群运行
            try {
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }
        } else {
            //本地模式运行
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("stormKafka",config,builder.createTopology());
            Utils.sleep(2*60*1000);
            cluster.killTopology("Topotest1121");
            cluster.shutdown();
        }


//        BrokerHosts brokerHosts = new ZkHosts("hadoop1:2181");
//        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "test01", "/brokers", "kafkaSpout");
//        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
//        Properties properties = new Properties();
//        properties.put("bootstrap.servers", "hadoop1:9092");
//        properties.put("serializer.class", "kafka.serializer.StringEncoder");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        KafkaBolt kafkaBolt = new KafkaBolt();
//        kafkaBolt.withProducerProperties(properties);
//
//        TopologyBuilder builder = new TopologyBuilder();
//        builder.setSpout("kafkaSpout", new KafkaSpout(spoutConfig));
//        builder.setBolt("msgKafkaBolt", kafkaBolt).shuffleGrouping("kafkaSpout");
//        builder.setBolt("myboltl", new MyKafkaBolt1()).shuffleGrouping("msgKafkaBolt");
//        Config config = new Config();
//        config.put("kafka.broker.properties", properties);
//        config.put("bootstrap.servers", "hadoop1:9092");
//        config.put("topic", "test01");
//        config.setDebug(false);
//        config.setNumWorkers(3);
//        LocalCluster localCluster = new LocalCluster();
//        localCluster.submitTopology("stormKafka", config, builder.createTopology());
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
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




    }
}
