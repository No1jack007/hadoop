package com.zhang.hadoop.service.hdfs.myHDFS;

import com.zhang.hadoop.service.hdfs.myHDFS.ClientNameNodeProtocol;

/**
 * Created by zhang yufei on 2018/8/19.
 */
public class MyNameNode implements ClientNameNodeProtocol {

    @Override
    public String getMetaData(String path){
        return path+": 3 - {BLK_1,BLk_2} ....";
    }

}
