package com.zhang.hadoop.spark

import java.sql.DriverManager

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object JdbcRDDDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("JdbcRDDDemo")
    val sc = new SparkContext(conf)
    def getConnection()={
      Class.forName("com.mysql.jdbc.Driver").newInstance()
      DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata?serverTimezone=UTC", "root", "root")
    }
    val jdbcRDD=new JdbcRDD(
      sc,
      getConnection,
      "select counts,location from location_info where counts >=? and counts <=?",
      1,2000,2,
      rs=>{
        val counts=rs.getInt(1)
        val location=rs.getString(2)
        (counts,location)
      }
    )

    val jrdd=jdbcRDD.collect()
    println(jdbcRDD.collect().toBuffer)

    sc.stop()
  }

}
