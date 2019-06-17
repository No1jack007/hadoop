package com.zhang.hadoop.sparkStreaming

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StateFullWordCount {

  val updateFunction = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
    iter.flatMap { case (x, y, z) => Some(y.sum + z.getOrElse(0)).map(m => (x, m)) }
  }

  def main(args: Array[String]): Unit = {
    //StreamingContext
    val conf = new SparkConf().setAppName("StreamingWordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(5))
    //接收数据
    val ds = ssc.socketTextStream("hadoop1-1", 8888)
    //DStream是一个特殊的RDD
    val result = ds.flatMap(_.split("  ")).map((_, 1)).updateStateByKey(updateFunction,new HashPartitioner(sc.defaultParallelism),true)
    //打印结果
    result.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
