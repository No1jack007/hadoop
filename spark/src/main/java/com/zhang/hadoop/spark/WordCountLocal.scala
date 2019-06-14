package com.zhang.hadoop.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object WordCountLocal {

  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir","D:\\0-program\\program\\hadoop-2.6.5" )

    /**
      * 第一步：创建Spark的配置对象SparkConf，设置Spark程序运行时的配置信息，
      * 例如说通过设置setMaster来设置程序要链接的Spark集群的Master的URL，
      * 如果设置为local，则代表Spark程序在本地运行。
      */
    val conf = new SparkConf //创建SparkConf对象
    conf.setAppName("wordCount") //设置应用程序的名称，在程序运行的监控界面可以看到名称
    conf.setMaster("local") //此时，程序在本地运行，不需要安装Spark集群

    /**
      * 第二步：创建SparkContext对象
      * SparkContext是Spark程序所有功能的唯一入口，无论是采用scala、java、Python，R等都
      * 必须有一个SparkContext。SparkContext核心作用：初始化Spark应用程序运行所需要的核心组件，包括
      * DAGScheduler，TaskScheduler、SchedulerBackend同时还会负责Spark程序往Master注册程序等。
      * SparkContext是这个Spark程序中最为至关重要的一个对象。
      */
    val sc = new SparkContext(conf)

    //设置检查点路径
    sc.setCheckpointDir("D:\\0-program\\test\\checkpoint")

    /**
      * 第三步：根据具体的数据源(HDFS、HBase、Local FS、DB、S3等)通过SparkContext创建RDD。
      * RDD的创建方式有三种：根据外部的数据源（HDFS）、根据Scala集合、其他的RDD操作。数据会被RDD划分成一系列的
      * Partitions，分配到每个Partition的数据属于一个Task的处理范畴
      */
    val lines = sc.textFile("D:\\0-program\\test\\wordCount.txt", 1)//

    /**
      * 第四步：对初始化的RDD进行Transformation级别处理，例如map、filter等高阶函数等的编程，来进行具体的数据计算。
      */
    /**
      * 4.1、对每一行的字符串拆分成单个的单词
      */
    val words = lines.flatMap { line => line.split(" ") } //对每一行的字符串进行单词拆分并把所有行的拆分结果通过flat合并成为一

    /**
      * 4.2、在单词拆分的基础上对每个单词实例计数为1，也就是word => (word,1)
      */
    val pairs = words.map { word => (word, 1) }

    //先缓存
    pairs.cache()
    //在持久化到checkpoint
    pairs.checkpoint()

    /**
      * 4.3、在每个单词实例计数为1基础之上统计每个单词在文件中出现的总次数
      */
    val wordCounts = pairs.reduceByKey(_+_) //对相同的key，进行value的累计

    wordCounts.foreach(map => println(map._1 +":"+ map._2))

    sc.stop()
  }

}
