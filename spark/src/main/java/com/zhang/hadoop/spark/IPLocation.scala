package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object IPLocation {

  def ip2Long(ip: String): Long = {
    val fragments = ip.split("[.]")
    var ipNum = 0L
    for (i <- 0 until fragments.length) {
      ipNum = fragments(i).toLong | ipNum << 8L
    }
    ipNum
  }

  def binarySearch(lines: Array[(String, String, String)], ip: Long): Int = {
    var low = 0
    var high = lines.length - 1
    while (low <= high) {
      val middle = (low + high) / 2
      if ((ip > lines(middle)._1.toLong) && (ip <= lines(middle)._2.toLong))
        return middle
      if (ip < lines(middle)._1.toLong)
        high = middle - 1
      else {
        low = middle + 1
      }
    }
    -1
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("IpLocation")
    val sc = new SparkContext(conf)
    var path = "D:\\0-program\\test\\"
    //    var path = "C:\\zhang\\workspace\\data\\"

    val ipRulesRdd = sc.textFile(path + "ip-2.txt").map(line => {
      val fields = line.split("\\|")
      val start_num = fields(2)
      val end_num = fields(3)
      val provience = fields(6)
      (start_num, end_num, provience)
    })
    //全部ip映射规则
    val ipRulesArray = ipRulesRdd.collect()
    //广播规则
    val ipRulesBroadcast = sc.broadcast(ipRulesArray)

    //加载要处理的数据
    val ipsRdd = sc.textFile(path + "access.log").map(line => {
      val fields = line.split("\\|")
      fields(1)
    })

    val result = ipsRdd.map(ip => {
      val ipNum = ip2Long(ip)
      val index = binarySearch(ipRulesBroadcast.value, ipNum)
      val length = ipRulesBroadcast.value.length
      if (index < length && index > -1) {
        try {
          val info = ipRulesBroadcast.value(index)
          info
        } catch {
          case exception: Exception => {
            println(index)
          }
        }
      } else {

      }
    })
    println(result.collect().toBuffer)

    sc.stop()
  }
}
