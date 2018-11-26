package com.zhang.hadoop.service.hdfs;

/**
 * Created by zhang yufei on 2018/8/19.
 */
public class MyNameNode implements ClientNameNodeProtocol{

    @Override
    public String getMetaData(String path){
        return path+": 3 - {BLK_1,BLk_2} ....";
    }

}
