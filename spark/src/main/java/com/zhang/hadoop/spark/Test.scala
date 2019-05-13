package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object Test {

  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "D:\\0-program\\program\\hadoop-2.6.5")
    System.setProperty("hadoop.home.dir", "C:\\zhang\\work\\hadoop-2.6.5")

    val conf = new SparkConf
    conf.setAppName("wordCount")
    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd=sc.parallelize(List(1,2,3,4,5,6,7),2)

    rdd.mapPartitionsWithIndex()


  }

  def func(index:Int,iter:Iterator[(Int)]):Iterator[String]={
    iter.toList.map(x=>"[partId:"+index+",val:"+x+"]").iterator
  }
}
