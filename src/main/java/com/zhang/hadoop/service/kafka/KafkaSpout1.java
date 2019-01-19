package com.zhang.hadoop.service.kafka;

/**
 * Created by zhang yufei on 2019/1/18.
 */

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Date Jun 10, 2015
 *
 * @Author dengjie
 *
 * @Note Data sources using KafkaSpout1 to consume Kafka
 */
public class KafkaSpout1 implements IRichSpout {

    /**
     *
     */
    private static final long serialVersionUID = -7107773519958260350L;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSpout1.class);

    SpoutOutputCollector collector;
    private ConsumerConnector consumer;
    private String topic;

    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        props.put("zookeeper.connect", "hadoop1:2181");
        props.put("group.id", "group1");
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

    public KafkaSpout1(String topic) {
        this.topic = topic;
    }

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    public void close() {
        // TODO Auto-generated method stub

    }

    public void activate() {
        this.consumer = Consumer.createJavaConsumerConnector(createConsumerConfig());
        Map<String, Integer> topickMap = new HashMap<String, Integer>();
        topickMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> streamMap = consumer.createMessageStreams(topickMap);
        KafkaStream<byte[], byte[]> stream = streamMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            String value = new String(it.next().message());
            LOGGER.info("(consumer)==>" + value);
            collector.emit(new Values(value), value);
        }
    }

    public void deactivate() {
        // TODO Auto-generated method stub

    }

    public void nextTuple() {
        // TODO Auto-generated method stub

    }

    public void ack(Object msgId) {
        // TODO Auto-generated method stub

    }

    public void fail(Object msgId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("KafkaSpout1"));
    }


    public Map<String, Object> getComponentConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }

}