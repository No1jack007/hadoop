package com.zhang.hadoop.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hbase.thirdparty.io.netty.handler.codec.http2.Http2FrameReader;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zhang yufei on 2018/11/22.
 */
@Service
public class HBaseService {

    private Configuration configuration;
    private Connection connection;
    private Table table;

    public HBaseService() throws Exception{
        configuration= HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop1");
        configuration.set("hbase.zookeeper.property.clientPort","2181");
        connection= ConnectionFactory.createConnection(configuration);
    }

    public void insert() throws Exception{
        table=connection.getTable(TableName.valueOf("user"));
//        Put put=new Put();
//        table.put(put);
    }

}
