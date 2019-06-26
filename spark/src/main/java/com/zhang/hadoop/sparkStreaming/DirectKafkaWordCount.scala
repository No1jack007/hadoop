package com.zhang.hadoop.sparkStreaming

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
  * kafka0.8版本直连写法
  */

object DirectKafkaWordCount {

  def main(args: Array[String]): Unit = {

    val host = "hadoop1-1"

    var zkQuorum = host + ":2181"
    var brokers = host + ":9092"
    var groupId = "g1"
    var topics = "test"

    val sparkConf = new SparkConf().setAppName("DirectKafkaWordCount")
    sparkConf.setMaster("local[*]")
    sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "5")
    sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

    val ssc = new StreamingContext(sparkConf, Seconds(2))

    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String, String](
      "metadata.broker.list" -> brokers,
      "group.id" -> groupId,
      "auto.offset.reset" -> "smallest"
    )

    val km = new KafkaManager(kafkaParams)

    val messages = km.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topicsSet)

    messages.foreachRDD(rdd => {
      if (!rdd.isEmpty()) {
        // 先处理消息
        processRdd(rdd)
        // 再更新offsets
        km.updateZKOffsets(rdd)
      }
    })

    ssc.start()
    ssc.awaitTermination()

  }

  def processRdd(rdd: RDD[(String, String)]): Unit = {
    val lines = rdd.map(_._2)
    val words = lines.map(_.split(" "))
    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
    wordCounts.foreach(println)
  }


}
