package com.zhang.hadoop.service.kafka;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by zhang yufei on 2019/1/18.
 */
public class MessageScheme implements Scheme {
    public List<Object> deserialize(byte[] arg0) {
        try {
            String msg = new String(arg0, "UTF-8");
            return new Values(msg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Fields getOutputFields() {
        return new Fields("msg");
    }
}
