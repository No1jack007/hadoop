package com.zhang.hadoop.sparkStreaming

import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object KafkaWordCount {

  val updateFunction = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
    iter.flatMap { case (x, y, z) => Some(y.sum + z.getOrElse(0)).map(m => (x, m)) }
  }

  def main(args: Array[String]): Unit = {
//    var Array(zkQuorum,group,topics,numThreads)=args;
    var zkQuorum="hadoop1-1:2181"
    var group="g1"
    var topics="test"
    var numThreads="2"
    val conf = new SparkConf().setAppName("KafkaWordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(5))
    sc.setCheckpointDir("D:\\0-program\\test\\checkpoint")
    val topicMap=topics.split(",").map((_,numThreads.toInt)).toMap
    val data=KafkaUtils.createStream(ssc,zkQuorum,group,topicMap)
    data.print()
    val words=data.map(_._2).flatMap(_.split("  "))
    val wordCount=words.map((_,1)).updateStateByKey(updateFunction,new HashPartitioner(sc.defaultParallelism),true)
    wordCount.print()
    ssc.start()
    ssc.awaitTermination()
  }

}
