package com.zhang.hadoop.service.hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by zhang yufei on 2018/7/29.
 */
@Service
public class HdfsClientService {

    FileSystem fs=null;
    Configuration configuration=null;


    public void HdfsClientDemo() throws Exception{
        configuration=new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.10.11:9000");
        fs= FileSystem.get(new URI("hdfs://192.168.10.11:9000"),configuration,"root");
    }

    public void test(){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void testUpload() throws Exception{
        fs.copyFromLocalFile(new Path("c:/临时/鬼 吹 灯(1-8加续).txt"),new Path("/access.log.copy"));
        fs.close();
    }


    public void testDownLoad() throws Exception{
        fs.copyToLocalFile(false,new Path("/access.log.copy"),new Path("D:/1缓存/hadoop/"),true);
        fs.close();
    }


    public void testConf(){
        Iterator<Map.Entry<String,String>> it=configuration.iterator();
        while (it.hasNext()){
            Map.Entry<String,String> ent=it.next();
            System.out.println(ent.getKey()+":"+ent.getValue());
        }
    }


    public void testMkdir() throws Exception{
        boolean mkdir=fs.mkdirs(new Path("/testmkdir"));
        System.out.println(mkdir);
    }


    public void testDelete() throws Exception{
        boolean flag=fs.delete(new Path("/testmkdir/aaa"),true);
        System.out.println(flag);
    }


    public void testLs() throws Exception{
        RemoteIterator<LocatedFileStatus> listFiles=fs.listFiles(new Path("/"),true);
        while (listFiles.hasNext()){
            LocatedFileStatus fileStatus=listFiles.next();
            System.out.println("bolicksize:"+fileStatus.getBlockSize());
            System.out.println("owner:"+fileStatus.getOwner());
            System.out.println("Replication:"+fileStatus.getReplication());
            System.out.println("Permission:"+fileStatus.getPermission());
            System.out.println("Name:"+fileStatus.getPath().getName());
            System.out.println("-------------------------");
            BlockLocation[] blockLocations=fileStatus.getBlockLocations();
            for(BlockLocation b:blockLocations){
                System.out.println("块起始偏移量："+b.getOffset());
                System.out.println("块长度："+b.getLength());
                System.out.println("块所在主机："+b.getHosts());
            }
        }
    }


    public void testUpload2() throws Exception{
        FSDataOutputStream outputStream=fs.create(new Path(""));
        FileInputStream inputStream=new FileInputStream("");
        IOUtils.copy(inputStream,outputStream);
    }


    public void testDownLoad2() throws Exception{
        FSDataInputStream inputStream=fs.open(new Path(("")));
        FileOutputStream outputStream=new FileOutputStream("");
        IOUtils.copy(inputStream,outputStream);
    }


    public void testRandomAccess() throws Exception{
        FSDataInputStream inputStream=fs.open(new Path(""));
        inputStream.seek(12);
        FileOutputStream outputStream=new FileOutputStream("");
        IOUtils.copy(inputStream,outputStream);
    }


}
