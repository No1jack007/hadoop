package com.zhang.hadoop.spark

import java.net.URL

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object UrlCountPartition {

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("UrlCount").setMaster("local")
    val sc=new SparkContext(conf)
    //rdd1将数据切分，元组中放的是(URL,1)
//    var path="D:\\0-program\\test\\"
    var path="C:\\zhang\\workspace\\data\\"
    val rdd1=sc.textFile(path+"urlTest.txt").map(line=>{
      val f=line.split("\t")
      (f(1),1)
    })
    val rdd2=rdd1.reduceByKey(_+_)
    val rdd3=rdd2.map(t=>{
      val url=t._1
      val host=new URL(url).getHost
      (host,url,t._2)
    })

//    rdd3.filter(_._1=="hao.360.com")
//    val rdd4=rdd3.filter(_._1=="hao.360.com").groupBy(_._1).mapValues(it=>{
//      it.toList.sortBy(_._3).reverse.take(3)
//    })

//    rdd3.repartition(3).saveAsTextFile(path+"out\\out1")

    val rdd4=rdd3.map(_._1).distinct().collect()

    println(rdd4.toBuffer)

    sc.stop()
  }

}

class HostPartitioner extends Partitioner{

  override def numPartitions: Int = ???

  override def getPartition(key: Any): Int = ???

}