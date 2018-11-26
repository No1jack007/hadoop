package com.zhang.hadoop.service.zooKeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by zhang yufei on 2018/6/10.
 */
public class SimpleZkClient {

    private static final String connectString="192.168.10.11:2181";
    private static final int sessionTimeout=2000;
    public ZooKeeper zkClient=null;

    public void init() throws Exception{
        zkClient=new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //收到时间通知后的回掉函数
                System.out.println(event.getType()+"----"+event.getPath());
                try {
                    zkClient.getChildren("/",true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //创建数据结点到zk中
    public  void testCreate() throws Exception{
        //参数1：要创建的节点的路径 参数2：节点的数据 参数3：节点的权限 参数4：节点类型
        String nodeCreated=zkClient.create("/idea","hello zk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //上传的数据可以是任何类型，但是都要转成byte；
    }

    //获取子节点

    public void getChildren() throws Exception{
        List<String> children=zkClient.getChildren("/",true);
        for (String child:children){
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    //查看状态是否存在

    public void testExist() throws Exception{
        Stat stat=zkClient.exists("/idea",false);
        System.out.println(stat==null?"not exist":"exist");
    }

    //获取znode的数据

    public void getData() throws Exception{
        byte[] data=zkClient.getData("/idea",false,null);
        System.out.println(new String(data));
    }

    //删除znode

    public void deleteZnode() throws Exception{
        //参数2：指定要删除的版本，-1表示所有版本
        zkClient.delete("/idea",-1);
    }

    //修改znode

    public void setZnode() throws Exception{
        //参数2：指定要删除的版本，-1表示所有版本
        zkClient.setData("/idea","update ".getBytes(),-1);
        byte[] data=zkClient.getData("/idea",false,null);
        System.out.println(new String(data));
    }
}
