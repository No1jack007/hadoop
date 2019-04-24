package com.zhang.hadoop.spark.hdfs.myHDFS;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

/**
 * Created by zhang yufei on 2018/8/21.
 */
public class MyHdfsClient {

    public void hdfsClient() throws Exception {
        ClientNameNodeProtocol nameNode = RPC.getProxy(ClientNameNodeProtocol.class, 1L, new InetSocketAddress("localhost", 8888), new Configuration());
        String metaDate= nameNode.getMetaData("/angela.mygirl");
        System.out.println(metaDate);
    }
}
