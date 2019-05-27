package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object UserLocation {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("UrlCount").setMaster("local")
    val sc = new SparkContext(conf)
    //    val rdd1=sc.textFile("D:\\0-program\\test\\phone.txt").map(_.split(",")).map(x=>(x(0),x(1),x(2),x(3)))
//    val path="D:\\0-program\\test\\phone.txt"
    val path="C:\\zhang\\workspace\\data\\phone.txt"
    val rdd1 = sc.textFile(path).map(line => {
      val fields = line.split(",")
      val eventType = fields(3)
      val time = fields(1)
      val timeLong = if (eventType == "1") {
        -time.toLong
      } else if (eventType == "0") {
        time.toLong
      }
      println(timeLong)
      (fields(0) + "_" + fields(2), timeLong)
    })
//    println(rdd1.collect().toBuffer)
    val result=rdd1.groupBy(_._1).mapValues({
//      println(_)
//      _.foldLeft(0L)(_ + _._2)
      ("1",_)
   })
    println(result.collect().toBuffer)
    sc.stop()
  }

}
