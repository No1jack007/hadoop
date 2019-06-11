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
      DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata", "root", "root")
    }
    val jdbcRDD=new JdbcRDD(
      sc,
      getConnection,
      "select * from ta where id >=? and id <=?",
      1,4,2,
      rs=>{
        val id=rs.getInt(1)
        val code=rs.getString(2)
        (id,code)
      }
    )

    val jrdd=jdbcRDD.collect()
    println(jdbcRDD.collect().toBuffer)

    sc.stop()
  }

}
