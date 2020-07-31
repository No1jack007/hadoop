package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.{PairRDDFunctions}

object Test {

  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "D:\\0-program\\program\\hadoop-2.6.5")
    //    System.setProperty("hadoop.home.dir", "C:\\zhang\\work\\hadoop-2.6.5")

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

    val rdd7=sc.parallelize(List("dog","cat","gnu","salmon","rabbit","turkey","wolf","bear","bee"),3)
    val rdd8=sc.parallelize(List(1,1,2,2,2,1,2,2,2),3)
    val rdd9=rdd8.zip(rdd7)
    println("zip\t" + rdd9.collect().toBuffer)

    val rdd10=rdd9.combineByKey(List(_),(x: List[String],y: String)=>{x :+y},(a: List[String],b: List[String])=>{a++b})
    println("combineByKey List\t" + rdd10.collect().toBuffer)

    val rdd11=sc.textFile("D:\\0-program\\test\\wordCount.txt", 2).flatMap(_.split(" ").map((_, 1))).combineByKey(x => x,combineByKey_partition,combineByKey_shuffle).collect()

    val rdd12=rdd9.combineByKey(List(_),combineByKey_func1,combineByKey_func2).collect()

    val rdd13=rdd.repartition(4)
    val result8 = rdd13.mapPartitionsWithIndex(func1).collect()
    println("repartition" + "\t" + result8.toBuffer)

    val rdd14=rdd.coalesce(2,true)
    val result9 = rdd14.mapPartitionsWithIndex(func1).collect()
    println("coalesce" + "\t" + result9.toBuffer)

    val rdd15=sc.parallelize(List(("a",1),("b",2),("c",3)))
    println("collectAsMap" + "\t" + rdd15.collectAsMap())

    val rdd16=sc.parallelize(List(("a",1),("b",2),("b",2),("c",2),("c",1)))
    println("countByKey" + "\t" + rdd16.countByKey())

    println("countByValue" + "\t" + rdd16.countByValue())

    val rdd17=sc.parallelize(List(("e",5),("c",3),("d",4),("c",2),("a",1),("b",6)))
    println("filterByRange" + "\t" + rdd17.filterByRange("b","d").collect.toBuffer)

    val rdd18=sc.parallelize(List(("a","1 2"),("b","3 4")))
    println("flatMapValues" + "\t" + rdd18.flatMapValues(_.split(" ")).collect().toBuffer)

    val rdd19=sc.parallelize(List("dog","wolf","cat","bear"),2)
    println("foldByKey"+"\t"+rdd19.map(x=>(x.length,x)).foldByKey("")(_+_).collect().toBuffer)

    val rdd20=sc.makeRDD(List(("a","1"),("b","2"),("c","3"),("a","2")))
    println("groupByKey"+"\t"+rdd20.groupByKey().collect().toBuffer)






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

  def combineByKey_partition(m: Int, n: Int):Int={
    println("combineByKey_partition\tm:\t"+m+"\t"+"combineByKey_partition\tn:\t"+n)
    m + n
  }

  def combineByKey_shuffle(a: Int, b: Int):Int={
    println("combineByKey_partition\ta:\t"+a+"\t"+"combineByKey_partition\tb:\t"+b)
    a + b
  }

  def combineByKey_func1(x: List[String],y: String):List[String]={
    println("combineByKey_func1\tx:\t"+x.toBuffer+"\t"+"combineByKey_func1\ty:\t"+y)
    x :+y
  }

  def combineByKey_func2(a: List[String],b: List[String]):List[String]={
    println("combineByKey_func2\tx:\t"+a.toBuffer+"\t"+"combineByKey_func2\ty:\t"+b.toBuffer)
    a++b
  }

}
