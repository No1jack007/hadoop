package com.zhang.hadoop.scala

import scala.collection.mutable.ListBuffer

/**
  * Created by zhang yufei on 2019/3/3.
  */
object Test {

  def main(args: Array[String]) {
    println("Hello, world!") // 输出 Hello World
    for (i <- 1 to 3) {
      println(i);
    }
    for (i <- 1 until 3) {
      println(i);
    }
    println(1 - (10));
    println(m1(2, 3))
    val r = m1(3, 4)
    println(r)
    println(f1(1,2))

    val list=List(1,2,3)
    println("list")
    println(list(0))
    //可变list
    val lb=ListBuffer(1,2,3)
    lb(1)=200
    println(lb(1))
    println(lb.map(_*10))
    //拼接list
    println(0::list)
  }

  def m1(a: Int, b: Int) = {
    a * b
  }

  var f1=(x: Int,y:Int)=>{
    x+y
  }


}
