package com.zhang.hadoop.scala.highFunction

class HighFunc {

}

object HighFunc {

  val func: Int => Int = { x => x * x }

  def multiply(x:Int):Int={x*x}

  def multi()=(x:Int)=>{
    x*x
  }

  def klh(x:Int)(y:Int)=x*y

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3, 4, 5)
    val a1=arr.map(multiply)
    println(a1.toBuffer)
    val a=klh(3)(4)
    println(a)
    println(multiply(2))
    println(klh(3)(4))
  }
}
