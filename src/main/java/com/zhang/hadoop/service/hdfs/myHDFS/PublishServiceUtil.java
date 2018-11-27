package com.zhang.hadoop.service.hdfs.myHDFS;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.Server;

/**
 * Created by zhang yufei on 2018/8/19.
 */
public class PublishServiceUtil {

    public static void main(String[] args) throws Exception {
        Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("localhost")
                .setPort(8888)
                .setProtocol(ClientNameNodeProtocol.class)
                .setInstance(new MyNameNode());

        Server server = builder.build();
        server.start();

    }
}
