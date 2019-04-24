package com.zhang.hadoop.spark.hdfs.myHDFS;

/**
 * Created by zhang yufei on 2018/8/19.
 */
public interface ClientNameNodeProtocol {

    public static final long versionID=1L;

    public String getMetaData(String path);
}
