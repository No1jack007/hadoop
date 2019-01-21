package com.zhang.hadoop.service.kafka.order;

import com.zhang.hadoop.service.redis.JedisUtil;
import jodd.json.JsonParser;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by zhang yufei on 2019/1/21.
 */
public class ParserOrderMqBolt extends BaseRichBolt {

    OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        //获取kafkaSpout发送过来的数据
        String string = new String((byte[]) tuple.getValue(0));
        //解析json
        OrderInfo orderInfo = new JsonParser().parse(string, OrderInfo.class);
        //整个网站，各个业务线，各个品类，各个店铺，各个品牌，每个商品
        JedisUtil jedisUtil = JedisUtil.getInstance();
        JedisUtil.Strings strings = jedisUtil.new Strings();
        //获取整个网站的金额统计指标
        String totalAmount=strings.get("totalAmount");
        //获取商品所属业务线的指标信息
        String bid=getByProductId(orderInfo.getProductId());
        String businessAmount=strings.get(bid+"businessAmount");
        String totalAmount=strings.get("totalAmount");
        String totalAmount=strings.get("totalAmount");
    }

    public String getByProductId(String productI){
        //从redis获取商品所属的业务编号
        return "3c";
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("messge"));
    }


}
