package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object UrlCount {

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("UrlCount").setMaster("local")
    val sc=new SparkContext(conf)

    //rdd1将数据切分，元组中放的是(URL,1)
    val rdd1=sc.textFile("").map(line=>{
      val f=line.split("\t")
      (f(1),1)
    })

    val rdd2=rdd1.reduceByKey(_+_)





  }

}
