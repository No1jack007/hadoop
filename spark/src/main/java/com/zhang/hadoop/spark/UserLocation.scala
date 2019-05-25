package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object UserLocation {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("UrlCount").setMaster("local")
    val sc = new SparkContext(conf)
    //    val rdd1=sc.textFile("D:\\0-program\\test\\phone.txt").map(_.split(",")).map(x=>(x(0),x(1),x(2),x(3)))
    val rdd1 = sc.textFile("D:\\0-program\\test\\phone.txt").map(line => {
      val fields = line.split(",")
      val eventType = fields(3)
      val time = fields(1)
      val timeLong = if (eventType == "1") {
        -time.toLong
      } else if (eventType == "1") {
        time.toLong
      }
      (fields(0) + "_" + fields(2), timeLong)
    })
    sc.stop()
  }

}
