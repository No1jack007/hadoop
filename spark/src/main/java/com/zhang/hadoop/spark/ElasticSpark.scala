package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object ElasticSpark {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ElasticSpark").setMaster("local")
    conf.set("es.nodes","192.168.1.111")
    conf.set("es.port","9200")
    conf.set("es.index.auto.create","true")
    val sc = new SparkContext(conf)
    val start=1453998397
    val end=1463998399

//    val rdd1=sc.esRDD()
  }

}
