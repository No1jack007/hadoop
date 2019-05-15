package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object Test {

  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "D:\\0-program\\program\\hadoop-2.6.5")
    //    System.setProperty("hadoop.home.dir", "C:\\zhang\\work\\hadoop-2.6.5")

    val conf = new SparkConf
    conf.setAppName("wordCount")
    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(1, 2, 3, 4, 5, 6, 7), 3)

    val result1 = rdd.mapPartitionsWithIndex(func1).collect()
    println("mapPartitionsWithIndex"+"\t"+result1.toBuffer)

    val result2 = rdd.mapPartitions(func2).collect();
    println("mapPartitions"+"\t"+result2.toBuffer)

    val result3=rdd.aggregate(0)(_+_,_+_)
    println("aggregate"+"\t"+result3)




  }

  def func1(index: Int, iter: Iterator[(Int)]): Iterator[String] = {
    iter.toList.map(x => "[partId:" + index + ",val:" + x + "]").iterator
  }

  def func2(iterator: Iterator[(Int)]): Iterator[Int] = {
    var result = List[Int]()
    var i = 0
    while (iterator.hasNext) {
      i += iterator.next()
    }
    result.::(i).iterator
  }

}
