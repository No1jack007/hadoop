package com.zhang.hadoop.service.storm.stormWordCount;


import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

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
        spoutOutputCollector.emit(new Values("hello word"));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("love"));
    }

}
