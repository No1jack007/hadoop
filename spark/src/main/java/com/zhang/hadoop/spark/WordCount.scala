package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {
    //非常重要，是通向spark集群的入口
//    val host="hadoop1-1"
//    val path="D:\\0-program\\work"
    val host="hadoop1"
    val path="C:\\zhang\\workspace"
    val conf=new SparkConf().setJars(Array(path+"\\idea\\hadoop\\spark\\target\\spark-0.0.1-SNAPSHOT.jar")).setMaster("spark://"+host+":7077").setAppName("WC")
    val sc=new SparkContext(conf)
//    sc.textFile(args(0)).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).sortBy(_._2,false).saveAsTextFile(args(1))

    sc.textFile("hdfs://"+host+":9000/data1").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).sortBy(_._2,false).saveAsTextFile("hdfs://"+host+":9000/data1-out-1")

    sc.stop()
  }

}
