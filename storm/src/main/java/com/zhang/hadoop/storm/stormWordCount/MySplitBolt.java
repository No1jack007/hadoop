package com.zhang.hadoop.storm.stormWordCount;



import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by zhang yufei on 2018/12/12.
 */
public class MySplitBolt extends BaseRichBolt {

    OutputCollector outputCollector;

    //初始化方法
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector=outputCollector;
    }

    //storm框架while(true)execute循环调用，传入tuple
    @Override
    public void execute(Tuple tuple) {
        String line=tuple.getString(0);
        String wordArray[]=line.split(" ");
        for(String word:wordArray){
            outputCollector.emit(new Values(word,1));
        }
        outputCollector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word","num"));
    }
}
