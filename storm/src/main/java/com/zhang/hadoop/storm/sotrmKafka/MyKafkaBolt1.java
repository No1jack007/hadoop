package com.zhang.hadoop.storm.sotrmKafka;


import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by zhang yufei on 2019/1/10.
 */
public class MyKafkaBolt1 extends BaseRichBolt {

    OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector=outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
//        String line=new String((Byte)tuple.getValue(0));
        System.out.println(tuple.getValues());
        Object object=tuple.getValue(0);
        System.out.println("获取到结果："+object);
        outputCollector.emit(new Values(object,1));
        outputCollector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("messge"));
    }
}
