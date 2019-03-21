package com.zhang.hadoop.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zhang yufei
 * @create: 2019-03-13 17:56
 **/
@Service
public class HBaseUtil {

    private Configuration configuration;
    private Connection connection;
    private Table table;

    public HBaseUtil() throws Exception {
//    public void init() throws Exception{
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop1-1");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        connection = ConnectionFactory.createConnection(configuration);
    }

//    @Bean
//    public HbaseTemplate hbaseTemplate(@Value("${hbase.zookeeper.quorum}") String quorum,
//                                       @Value("${hbase.zookeeper.port}") String port) {
//        HbaseTemplate hbaseTemplate = new HbaseTemplate();
//        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
//        conf.set("hbase.zookeeper.quorum", quorum);
//        conf.set("hbase.zookeeper.port", port);
//        hbaseTemplate.setConfiguration(conf);
//        hbaseTemplate.setAutoFlush(true);
//        return hbaseTemplate;
//    }


    /**
     * 插入记录（单行单列族-单列单值）
     *
     * @param tableName         表名
     * @param row               行名
     * @param columnFamily      列族名
     * @param column            列名
     * @param value             值
     */
    public void insertOneRecord(String tableName, String row, String columnFamily, String column, String value) throws Exception {
        TableName name = TableName.valueOf(tableName);
        table = connection.getTable(name);
        Put put = new Put(Bytes.toBytes(row));
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
        table.put(put);
        table.close();
    }


}
