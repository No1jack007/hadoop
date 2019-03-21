package com.zhang.hadoop.scala

class Dog {

}
object Dog {

  def apply()={
    println("apply invoked")
  }

  def apply(name:String):String={
    println(name)
    name
  }

  def main(args: Array[String]): Unit = {
    val d=Dog()
    println(d)
    val dd=Dog("dog")
    println(dd)
  }
}
