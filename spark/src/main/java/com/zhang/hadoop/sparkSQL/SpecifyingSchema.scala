package com.zhang.hadoop.sparkSQL

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object SpecifyingSchema {

  def main(args: Array[String]): Unit = {

    System.setProperty("user.name", "hadoop")

//    val path = "C:\\zhang\\workspace"
//    val host = "hadoop1-1"
    val path="C:\\zhang\\workspace\\data\\"
    val host="hadoop1"

    val conf = new SparkConf().setAppName("SQLDemo").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val personRdd = sc.textFile("hdfs://" + host + ":9000/data8").map(_.split(","))
    //通过StructType直接指定么个字段的schema
    val schema = StructType(
      List(
        StructField("id", IntegerType, true),
        StructField("name", StringType, true),
        StructField("age", IntegerType, true)
      )
    )
    //将RDD映射到rowRDD
    val rowRDD = personRdd.map(p => Row(p(0).toInt , p(1).trim, p(2).toInt))
    //将schema信息应用到rowRDD上
    val personDataFrame=sqlContext.createDataFrame(rowRDD,schema)
    //注册表
    personDataFrame.registerTempTable("t_person")
    //执行sql
    val df=sqlContext.sql("select * from t_person order by age desc limit 4")
    //将结果以JSON的方式存储到指定位置
    df.write.json(path+"sqlout-1")
    sc.stop()

  }

}
