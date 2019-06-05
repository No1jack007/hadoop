package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object CustomSort {

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("CustomSort").setMaster("local[2]")
    val sc=new SparkContext(conf)
    val rdd1=sc.parallelize(List(("yuihatao",90,28,1),("angelababy",90,27,2),("JuJingYi",95,22,3)))
    rdd1.sortBy(_._2)


  }

}
