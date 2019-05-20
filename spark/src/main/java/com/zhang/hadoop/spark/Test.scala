package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.{PairRDDFunctions}

object Test {

  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "D:\\0-program\\program\\hadoop-2.6.5")
    //    System.setProperty("hadoop.home.dir", "C:\\zhang\\work\\hadoop-2.6.5")
    //    System.setProperty("_JAVA_OPTIONS", "-Xmx512M")

    val conf = new SparkConf
    conf.setAppName("test")
    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(1, 2, 3, 4, 5, 6, 7), 3)

    val result1 = rdd.mapPartitionsWithIndex(func1).collect()
    println("mapPartitionsWithIndex" + "\t" + result1.toBuffer)

    val result2 = rdd.mapPartitions(func2).collect();
    println("mapPartitions" + "\t" + result2.toBuffer)

    val result3 = rdd.aggregate(0)(_ + _, _ + _)
    println("aggregate sum" + "\t" + result3)

    val arr = Array(1, 2, 3)
    println("max\t" + arr.max)

    println("max\t" + arr.reduce(math.max(_, _)))

    val result4 = rdd.aggregate(0)(math.max(_, _), math.max(_, _))
    println("aggregate max" + "\t" + result4)

    val rdd2 = sc.parallelize(List("a", "b", "c", "d", "e", "f"), 2)

    val result5 = rdd2.aggregate("|")(_ + _, _ + _)
    println("aggregate" + "\t" + result5)

    val rdd3 = sc.parallelize(List("12", "23", "345", "4567"), 2)
    val result6 = rdd3.aggregate("")((x, y) => math.max(x.length, y.length).toString, (x, y) => x + y)
    println("aggregate\t" + result6)

    val rdd4 = sc.parallelize(List(("cat" -> 2), ("cat" -> 5), ("mouse" -> 4), ("cat" -> 12), ("dog" -> 12), ("mouse" -> 2)), 2)
    val result7 = rdd4.aggregateByKey(0)(math.max(_, _), _ + _).collect()
    println("aggregateByKey\t" + result7.toBuffer)

    val rdd5 = sc.textFile("D:\\0-program\\test\\wordCount.txt").flatMap(_.split(" ").map((_, 1))).reduceByKey(_ + _).collect()

    val rdd6 = sc.textFile("D:\\0-program\\test\\wordCount.txt", 2).flatMap(_.split(" ").map((_, 1))).combineByKey(x => x + 10, (m: Int, n: Int) => (m + n), (a: Int, b: Int) => (a + b)).collect()
    println("combineByKey\t" + rdd6.toBuffer)


    sc.stop()
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
