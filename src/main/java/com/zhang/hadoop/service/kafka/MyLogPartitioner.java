package com.zhang.hadoop.service.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * Created by zhang yufei on 2018/12/28.
 */
public class MyLogPartitioner implements Partitioner {

    public MyLogPartitioner (VerifiableProperties props) {

    }

    @Override
    public int partition(Object o, int i) {
        return 1;
    }
}
