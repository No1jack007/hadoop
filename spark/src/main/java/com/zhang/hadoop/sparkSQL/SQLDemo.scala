package com.zhang.hadoop.sparkSQL

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object SQLDemo {

  def main(args: Array[String]): Unit = {

    System.setProperty("user.name", "hadoop")

    val path = "C:\\zhang\\workspace"
    //    val host = "hadoop1-1"
    val host = "hadoop1"

    val conf = new SparkConf().setAppName("SQLDemo").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val personRdd = sc.textFile("hdfs://" + host + ":9000/data8").map(line => {
      val fields = line.split(",")
      Person(fields(0).toLong, fields(1), fields(2).toInt)
    })
    //导入隐式转换，不导入无法将rdd转换为DataFrames
    import sqlContext.implicits._
    val personDf = personRdd.toDF()

    personDf.registerTempTable("person")

    sqlContext.sql("select * from person where age >=0 order by age desc limit 2")

    sc.stop()
  }

}

case class Person(id: Long, name: String, age: Int)
