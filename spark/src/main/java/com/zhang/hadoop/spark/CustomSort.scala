package com.zhang.hadoop.spark

import org.apache.spark.{SparkConf, SparkContext}

object OrderContext {

  implicit val girlOrdering=new Ordering[Girl_1] {
    override def compare(x: Girl_1, y: Girl_1): Int = {
      if (x.faceValue > y.faceValue) 1
      else if (x.faceValue == y.faceValue) {
        if (x.age > y.age) -1 else 1
      } else {
        -1
      }
    }
  }

}

object CustomSort {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CustomSort").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(List(("yuihatao", 90, 28, 1), ("angelababy", 90, 27, 2), ("JuJingYi", 95, 22, 3)))
    val rdd2 = rdd1.sortBy(x => Girl(x._2, x._3), false)
    println(rdd2.collect().toBuffer)
    import OrderContext._
    val rdd3 = rdd1.sortBy(x => Girl_1(x._2, x._3), false)
    println(rdd3.collect().toBuffer)
    sc.stop()
  }

}

case class Girl(val faceValue: Int, val age: Int) extends Ordered[Girl] with Serializable {
  override def compare(that: Girl): Int = {
    if (this.faceValue == that.faceValue) {
      that.age - this.age
    } else {
      this.faceValue - that.faceValue
    }
  }
}

case class Girl_1(faceValue: Int, age: Int) extends Serializable

