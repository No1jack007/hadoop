package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object UserLocation {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("UrlCount").setMaster("local")
    val sc = new SparkContext(conf)
    //    val rdd1=sc.textFile("D:\\0-program\\test\\phone.txt").map(_.split(",")).map(x=>(x(0),x(1),x(2),x(3)))
    var path = "D:\\0-program\\test\\phone.txt"
    //    path="C:\\zhang\\workspace\\data\\phone.txt"

    val rdd1 = sc.textFile(path).map(line => {
      val fields = line.split(",")
      val eventType = fields(3)
      val time = fields(1)
      val timeLong = if (eventType == "1") {
        -time.toInt
      } else if (eventType == "0") {
        time.toInt
      }
      println(timeLong)
      (fields(0) + "_" + fields(2), timeLong)
    })
    println(rdd1.collect().toBuffer)

    val rdd2 = rdd1.groupBy(_._1).mapValues(x => {
      println(x)
      var v1 = 0;
      x.foreach(a => {
        println(a._2)
        v1 = Integer.parseInt(a._2.toString) + v1
      })
      v1
    })
    //    val result=rdd1.groupBy(_._1).mapValues(_.foldLeft(0)(_ +_._2))

    val rdd3 = rdd2.map(t => {
      val mobile_bs = t._1
      val mobile = mobile_bs.split("_")(0)
      val lac = mobile_bs.split("_")(1)
      val time = t._2
      (mobile, lac, time)
    })
    println(rdd3.collect().toBuffer)

    val rdd4 = rdd3.groupBy(_._1)
    println(rdd4.collect().toBuffer)

    val rdd5=rdd4.mapValues(it=>{
      it.toList.sortBy(_._3).reverse.take(1)
    })

    println(rdd5.collect().toBuffer)
    sc.stop()
  }

  def f1(a: String, b: AnyVal): Long = {
    0L
  }

}
