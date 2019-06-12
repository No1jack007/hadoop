package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {
    //非常重要，是通向spark集群的入口
    val conf=new SparkConf().setAppName("WC").setMaster("spark://hadoop1-1:7077").setJars(Array("D:\\0-program\\work\\idea\\hadoop\\spark\\target\\spark-0.0.1-SNAPSHOT.jar.original"))
    val sc=new SparkContext(conf)
//    sc.textFile(args(0)).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).sortBy(_._2,false).saveAsTextFile(args(1))

    sc.textFile("hdfs://hadoop1-1:9000/data1").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).sortBy(_._2,false).saveAsTextFile("hdfs://hadoop1-1:9000/data1-out-1")

    sc.stop()
  }

}
