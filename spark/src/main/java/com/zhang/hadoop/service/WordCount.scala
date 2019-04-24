package com.zhang.hadoop.service

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {
    //非常重要，是通向spark集群的入口
    val conf=new SparkConf().setAppName("WC")
    val sc=new SparkContext(conf)
    sc.textFile(args(0)).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).sortBy(_._2,false).saveAsTextFile(args(1))
    sc.stop()
  }

}
