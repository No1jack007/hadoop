package com.zhang.hadoop.service.kafka;

import org.apache.storm.spout.Scheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
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

    @Override
    public List<Object> deserialize(ByteBuffer byteBuffer) {
        return null;
    }

    public Fields getOutputFields() {
        return new Fields("msg");
    }
}
