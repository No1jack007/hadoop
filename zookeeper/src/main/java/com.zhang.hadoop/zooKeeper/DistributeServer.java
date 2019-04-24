package com.zhang.hadoop.zooKeeper;

import org.apache.zookeeper.*;

/**
 * Created by zhang yufei on 2018/6/12.
 */
public class DistributeServer {

    private static final String connectString="192.168.10.11:2181";
    private static final int sessionTimeout=2000;
    private static final String parentNode="/servers";
    private ZooKeeper zk=null;
    private static final String hoset1="192.168.10.11";

    //创建到zk的客户端连接
    public void getConnect() throws Exception{
        zk=new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //收到时间通知后的回掉函数
                System.out.println(event.getType()+"----"+event.getPath());
                try {
                    zk.getChildren("/",true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void registerServer(String hostName) throws Exception{
        String create=zk.create(parentNode+"/server",hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostName+" is online "+create);
    }

    public void handelBussiness(String hostName) throws InterruptedException {
        System.out.println(hostName+"start working...");
        Thread.sleep(Long.MAX_VALUE);
    }


    public static void main(String[] args) throws Exception{
        //获取zk连接
        DistributeServer server=new DistributeServer();
        server.getConnect();
        //理由zk连接注册服务器i西南西
        server.registerServer(hoset1);
        server.handelBussiness(hoset1);
    }

}
