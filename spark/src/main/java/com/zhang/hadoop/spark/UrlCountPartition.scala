package com.zhang.hadoop.spark

import java.net.URL

import org.apache.spark.{SparkConf, SparkContext}

object UrlCountPartition {

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("UrlCount").setMaster("local")
    val sc=new SparkContext(conf)
    //rdd1将数据切分，元组中放的是(URL,1)
    val rdd1=sc.textFile("D:\\0-program\\test\\urlTest.txt").map(line=>{
      val f=line.split("\t")
      (f(1),1)
    })
    val rdd2=rdd1.reduceByKey(_+_)
    val rdd3=rdd2.map(t=>{
      val url=t._1
      val host=new URL(url).getHost
      (host,url,t._2)
    })
    rdd3.filter(_._1=="hao.360.com")
    val rdd4=rdd3.filter(_._1=="hao.360.com").groupBy(_._1).mapValues(it=>{
      it.toList.sortBy(_._3).reverse.take(3)
    })
    println(rdd4.collect().toBuffer)
    sc.stop()
  }

}
