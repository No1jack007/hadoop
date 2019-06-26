package com.zhang.hadoop.sparkStreaming

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * kafka0.10版本直连写法
  */

object DirectStream {

  val updateFunc = (it: Iterator[(String, Seq[Int], Option[Int])]) => {
    it.map { case (w, s, o) => (w, s.sum + o.getOrElse(0)) }
  }

  def main(args: Array[String]): Unit = {

    val host = "hadoop1-1"

    var zkQuorum = host + ":2181"
    var brokers = host + ":9092"
    var groupId = "g1"
    var topics = Array("test")

    val conf: SparkConf = new SparkConf().setAppName("KafkaDirectStream").setMaster("local[*]")
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(5))
    ssc.checkpoint("D:\\0-program\\test\\checkpoint")
    //跟Kafka整合（直连方式，调用Kafka底层的消费数据的API）
    val brokerList = brokers
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> brokerList,
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> groupId,
      //这个代表，任务启动之前产生的数据也要读
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )
//    val topics = Array("test")

    /**
      * 指定kafka数据源
      * ssc：StreamingContext的实例
      * LocationStrategies：位置策略，如果kafka的broker节点跟Executor在同一台机器上给一种策略，不在一台机器上给另外一种策略
      * 设定策略后会以最优的策略进行获取数据
      * 一般在企业中kafka节点跟Executor不会放到一台机器的，原因是kakfa是消息存储的，Executor用来做消息的计算，
      * 因此计算与存储分开，存储对磁盘要求高，计算对内存、CPU要求高
      * 如果Executor节点跟Broker节点在一起的话使用PreferBrokers策略，如果不在一起的话使用PreferConsistent策略
      * 使用PreferConsistent策略的话，将来在kafka中拉取了数据以后尽量将数据分散到所有的Executor上
      * ConsumerStrategies：消费者策略（指定如何消费）
      *
      */
    val directStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(ssc,
      LocationStrategies.PreferConsistent, //Subscribe
      ConsumerStrategies.Subscribe[String, String](topics, kafkaParams)
    )
    val result: DStream[(String, Int)] = directStream.map(_.value()).flatMap(_.split(" "))
      .map((_, 1))
      .updateStateByKey(updateFunc, new HashPartitioner(ssc.sparkContext.defaultParallelism), true)
    result.print()
    directStream.foreachRDD(rdd => {
      val offsetRange: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      val maped: RDD[(String, String)] = rdd.map(record => (record.key, record.value))
      //计算逻辑
      maped.foreach(println)
      //循环输出
      for (o <- offsetRange) {
        println(s"${o.topic} ${o.partition} ${o.fromOffset} ${o.untilOffset}")
      }
    })

    ssc.start()
    ssc.awaitTermination()
  }

}
