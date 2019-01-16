package com.zhang.hadoop.service.storm.stormWordCount;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by zhang yufei on 2018/12/12.
 */
public class MySpout extends BaseRichSpout {

    SpoutOutputCollector spoutOutputCollector;

    //初始化方法
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
    }

    //storm框架while(true)循环调用nextTuple
    @Override
    public void nextTuple() {
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        spoutOutputCollector.emit(new Values("i am lilei,love hanmeimei"));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("love"));
    }

}
