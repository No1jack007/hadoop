package com.zhang.hadoop.service.zooKeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang yufei on 2018/6/12.
 */
public class DistributedClient {

    private static final String connectString="192.168.10.11:2181";
    private static final int sessionTimeout=2000;
    private static final String parentNode="/servers";
    private ZooKeeper zk=null;
    private static final String host1="192.168.10.11";
    private volatile List<String> serverList;

    //创建到zk的客户端连接
    public void getConnect() throws Exception{
        zk=new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //收到时间通知后的回掉函数
                System.out.println(event.getType()+"----"+event.getPath());
                try {
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取服务器信息列表
     * @throws Exception
     */
    public void getServerList() throws Exception{
        //获取服务器子节点信息，并且对父节点进行监听
        List<String> children=zk.getChildren(parentNode,true);
        //先创建一个局部的list来存服务器信息
        List<String> servers=new ArrayList<>();
        for (String child:children){
            //child只是子节点的节点名
            byte[] data=zk.getData(parentNode+"/"+child,false,null);
            servers.add(new String(data));
        }
        //把servers赋值给成员变量的serverList，已提供给个业务线程使用
        serverList=servers;
        //打印服务器列表
        System.out.println(serverList.toString());
    }

    public void handelBussiness(String hostName) throws InterruptedException {
        System.out.println(hostName+"start working...");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String [] args) throws Exception{
        DistributedClient client=new DistributedClient();
        client.getConnect();
        client.getServerList();
        client.handelBussiness(host1);
    }

}
