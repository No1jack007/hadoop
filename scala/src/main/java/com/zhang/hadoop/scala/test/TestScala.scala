package com.zhang.hadoop.scala.test

object TestScala {

  def main(args: Array[String]): Unit = {


    val listA = List(1,2,3)

    println(listA.foldLeft(0)((sum,i)=>sum+i))

  }

}
