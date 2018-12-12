package com.zhang.hadoop.service.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang yufei on 2018/11/22.
 */
@Service
public class HBaseService {

    private Configuration configuration;
    private Connection connection;
    private Table table;

    @Autowired
    public HbaseTemplate hbaseTemplate() {
        HbaseTemplate hbaseTemplate = new HbaseTemplate();
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop1");
        conf.set("hbase.zookeeper.port", "2181");
        hbaseTemplate.setConfiguration(conf);
        hbaseTemplate.setAutoFlush(true);
        return hbaseTemplate;
    }

    public void init() throws Exception {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop1");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        connection = ConnectionFactory.createConnection(configuration);
    }

    public void close() throws Exception {
        table.close();
        connection.close();
    }

    public void test() {
        try {
            this.init();
//            insert();
//            deleteData();
//            queryData();
//            scanData();
//            singleColumnValueFilter();
//            columnPrefixFilter();
//            rowKeyFilter();
            filterList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert() throws Exception {
        this.init();
        table = connection.getTable(TableName.valueOf("user"));
        Put put = new Put(Bytes.toBytes("2"));
        put.addColumn(Bytes.toBytes("info1"), Bytes.toBytes("name"), Bytes.toBytes("zhangyufei"));
        put.addColumn(Bytes.toBytes("info1"), Bytes.toBytes("age"), Bytes.toBytes("23"));
        put.addColumn(Bytes.toBytes("info2"), Bytes.toBytes("like"), Bytes.toBytes("niehuichao"));
        table.put(put);
        this.close();
    }

    public void deleteData() throws Exception {
        this.init();
        table = connection.getTable(TableName.valueOf("user"));
        Delete delete = new Delete(Bytes.toBytes("2"));
        delete.addFamily(Bytes.toBytes("info2"));
        delete.addColumn(Bytes.toBytes("info1"), Bytes.toBytes("age"));
        table.delete(delete);
        this.close();
    }

    public void queryData() throws Exception {
        this.init();
        table = connection.getTable(TableName.valueOf("user"));
        Get get = new Get(Bytes.toBytes("1"));
        Result result = table.get(get);
        byte[] value = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("name"));
        System.out.println(Bytes.toString(value));
        this.close();
    }

    public void scanData() throws Exception {
        this.init();
        table = connection.getTable(TableName.valueOf("user"));
        //全表扫描
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            byte[] value = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("name"));
            System.out.println(Bytes.toString(value));
            byte[] age = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("age"));
            System.out.println(Bytes.toString(age));
            byte[] like = result.getValue(Bytes.toBytes("info2"), Bytes.toBytes("like"));
            System.out.println(Bytes.toString(like));
        }
        //设置起始点扫面
        Scan scan1 = new Scan();
        ResultScanner scanner1 = table.getScanner(scan1);
        for (Result result : scanner1) {
            byte[] value = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("name"));
            System.out.println(Bytes.toString(value));
            byte[] age = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("age"));
            System.out.println(Bytes.toString(age));
            byte[] like = result.getValue(Bytes.toBytes("info2"), Bytes.toBytes("like"));
            System.out.println(Bytes.toString(like));
        }
        this.close();
    }

    //列值过滤去
    public void singleColumnValueFilter() throws Exception {
        this.init();
        table = connection.getTable(TableName.valueOf("user"));
        //创建过滤器
        SingleColumnValueExcludeFilter filter = new SingleColumnValueExcludeFilter(
                Bytes.toBytes("info1"),
                Bytes.toBytes("name"),
                CompareOperator.EQUAL,
                Bytes.toBytes("zhangyufei"));
        Scan scan = new Scan();
        //设置过滤器
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            byte[] value = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("name"));
            System.out.println(Bytes.toString(value));
            byte[] age = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("age"));
            System.out.println(Bytes.toString(age));
            byte[] like = result.getValue(Bytes.toBytes("info2"), Bytes.toBytes("like"));
            System.out.println(Bytes.toString(like));
        }
        this.close();
    }

    //列名过滤器
    public void columnPrefixFilter() throws Exception {
        this.init();
        table = connection.getTable(TableName.valueOf("user"));
        //创建过滤器
        ColumnPrefixFilter filter = new ColumnPrefixFilter(Bytes.toBytes("name"));
        Scan scan = new Scan();
        //设置过滤器
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            byte[] value = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("name"));
            System.out.println(Bytes.toString(value));
            byte[] age = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("age"));
            System.out.println(Bytes.toString(age));
            byte[] like = result.getValue(Bytes.toBytes("info2"), Bytes.toBytes("like"));
            System.out.println(Bytes.toString(like));
        }
        this.close();
    }

    //rowKey过滤器
    public void rowKeyFilter() throws Exception {
        this.init();
        table = connection.getTable(TableName.valueOf("user"));
        //创建过滤器
        Filter filter = new RowFilter(CompareOperator.EQUAL, new RegexStringComparator("^1"));
        Scan scan = new Scan();
        //设置过滤器
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            byte[] value = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("name"));
            System.out.println(Bytes.toString(value));
            byte[] age = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("age"));
            System.out.println(Bytes.toString(age));
            byte[] like = result.getValue(Bytes.toBytes("info2"), Bytes.toBytes("like"));
            System.out.println(Bytes.toString(like));
        }
        this.close();
    }

    //filterList
    public void filterList() throws Exception {
        this.init();
        table = connection.getTable(TableName.valueOf("user"));
        //创建过滤器
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);

        SingleColumnValueExcludeFilter filter1 = new SingleColumnValueExcludeFilter(
                Bytes.toBytes("info1"),
                Bytes.toBytes("name"),
                CompareOperator.EQUAL,
                Bytes.toBytes("zhangyufei"));
        filterList.addFilter(filter1);
        ColumnPrefixFilter filter2 = new ColumnPrefixFilter(Bytes.toBytes("name"));
        filterList.addFilter(filter2);

        Scan scan = new Scan();
        //设置过滤器
        scan.setFilter(filterList);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            byte[] value = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("name"));
            System.out.println(Bytes.toString(value));
            byte[] age = result.getValue(Bytes.toBytes("info1"), Bytes.toBytes("age"));
            System.out.println(Bytes.toString(age));
            byte[] like = result.getValue(Bytes.toBytes("info2"), Bytes.toBytes("like"));
            System.out.println(Bytes.toString(like));
        }
        this.close();
    }

    //rowKey过滤器
    public Map rowKeyFilter(String tableName, String rowKey, Map<String, List<String>> title) throws Exception {
        this.init();
        Map result = new HashMap<>();
        table = connection.getTable(TableName.valueOf(tableName));
        //创建过滤器
        Filter filter = new RowFilter(CompareOperator.EQUAL, new RegexStringComparator(rowKey));
        Scan scan = new Scan();
        //设置过滤器
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result row : scanner) {
            Map result1 = new HashMap<>();
            for (String key : title.keySet()) {
                Map result2 = new HashMap<>();
                for (String key1 : title.get(key)) {
                    byte[] value = row.getValue(Bytes.toBytes(key), Bytes.toBytes(key1));
                    result2.put(key1, Bytes.toString(value));
                }
                result1.put(key, result2);
            }
            result.put(Bytes.toString(row.getRow()), result1);
        }
        this.close();
        return result;
    }

    public void insert(String tableName, String rowKey, Map<String, Map> data) throws Exception {
        this.init();
        table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        for (Object key : data.keySet()) {
            for (Object key1 : data.get(key).keySet()) {
                put.addColumn(Bytes.toBytes(key.toString()), Bytes.toBytes(key1.toString()), Bytes.toBytes(data.get(key).get(key1).toString()));
            }
        }
        table.put(put);
        this.close();
    }

    public void delete(String tableName, String rewKey) throws Exception {
        this.init();
        table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rewKey));
        table.delete(delete);
        this.close();
    }
}
