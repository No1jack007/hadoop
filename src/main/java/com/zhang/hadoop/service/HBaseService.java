package com.zhang.hadoop.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hbase.thirdparty.io.netty.handler.codec.http2.Http2FrameReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.ObjectView;


/**
 * Created by zhang yufei on 2018/11/22.
 */
@Service
public class HBaseService {

    private Configuration configuration;
    private Connection connection;
    private Table table;

    public HBaseService() throws Exception {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop1");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        connection = ConnectionFactory.createConnection(configuration);
    }

    public void close() throws Exception {
        table.close();
        connection.close();
    }

    public void test(){
        try {
//            insert();
//            deleteData();
//            scanData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert() throws Exception {
        table = connection.getTable(TableName.valueOf("user"));
        Put put = new Put(Bytes.toBytes("2"));
        put.addColumn(Bytes.toBytes("info1"), Bytes.toBytes("name"), Bytes.toBytes("zhangyufei"));
        put.addColumn(Bytes.toBytes("info1"), Bytes.toBytes("age"), Bytes.toBytes("23"));
        put.addColumn(Bytes.toBytes("info2"), Bytes.toBytes("like"), Bytes.toBytes("niehuichao"));
        table.put(put);
        this.close();
    }

    public void deleteData() throws Exception {
        table = connection.getTable(TableName.valueOf("user"));
        Delete delete=new Delete(Bytes.toBytes("2"));
        delete.addFamily(Bytes.toBytes("info2"));
        delete.addColumn(Bytes.toBytes("info1"), Bytes.toBytes("age"));
        table.delete(delete);
        this.close();
    }

    public void scanData()throws Exception{
        table = connection.getTable(TableName.valueOf("user"));
        Get get=new Get(Bytes.toBytes("1"));
        Result result=table.get(get);
        byte[] value=result.getValue(Bytes.toBytes("info1"),Bytes.toBytes("name"));
        System.out.println(Bytes.toString(value));
        this.close();
    }



}
