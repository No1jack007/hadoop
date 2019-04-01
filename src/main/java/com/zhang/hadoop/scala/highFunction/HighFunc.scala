package com.zhang.hadoop.scala.highFunction

class HighFunc {

}

object HighFunc {

  val func: Int => Int = { x => x * x }

  def multiply(x:Int):Int={x*x}

  def multi()=(x:Int)=>{
    x*x
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3, 4, 5)
    val a1=arr.map(func)
    println(a1.toBuffer)
  }
}
