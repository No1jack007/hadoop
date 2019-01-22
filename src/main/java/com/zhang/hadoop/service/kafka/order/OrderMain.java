package com.zhang.hadoop.service.kafka.order;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang yufei on 2019/1/22.
 */
public class OrderMain {

    public static void main(String args[]) {

        String zkConnString = "hadoop1:2181";
        BrokerHosts hosts = new ZkHosts(zkConnString);
        SpoutConfig spoutConfig = new SpoutConfig(hosts, "orderMq1", "/brokers/topics", "spout1");
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        // 记录Spout读取进度所用的zookeeper的host,即记录offset位置的zk
        List<String> servers = new ArrayList<>();
        servers.add("hadoop1");
        spoutConfig.zkServers = servers;
        spoutConfig.zkPort = 2181;
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaspout", kafkaSpout);
        builder.setBolt("ParserOrderMqBolt", new ParserOrderMqBolt()).shuffleGrouping("kafkaspout");
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
            cluster.submitTopology("stormKafkaOrder", config, builder.createTopology());
            Utils.sleep(2 * 60 * 1000);
//            cluster.killTopology("stormKafkaOrder");
//            cluster.shutdown();
        }
    }
}
