package com.zhang.hadoop.service.storm.stormWordCount;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.springframework.stereotype.Service;

/**
 * Created by zhang yufei on 2018/12/12.
 */
@Service
public class WordCountMain {

//    public static void main(String args[]) {
    public void test(){
        try {
            //1.准备一个TopologyBuilder
            TopologyBuilder topologyBuilder = new TopologyBuilder();
            topologyBuilder.setSpout("mySpout", new MySpout(), 1);
            topologyBuilder.setBolt("myBolt1", new MySplitBolt(), 10).shuffleGrouping("mySpout");
            topologyBuilder.setBolt("myBolt2", new MyCountBolt(), 2).fieldsGrouping("myBolt1", new Fields("word"));

            //2.创建一个configuration，用来指定当前topology需要的worker数量
            Config config = new Config();
            config.setNumWorkers(2);

            //3.提交任务--两种模式
            // 集群模式
            //StormSubmitter.submitTopology("myWordCount", config, topologyBuilder.createTopology());
            //本地模式
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology("myWordCount", config, topologyBuilder.createTopology());
            Thread.sleep(10000);
            localCluster.killTopology("myWordCount");
            localCluster.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
