package com.zhang.hadoop.scala

class Chinese extends Animal {
  override def run(): Unit ={
    println("run")
  }
}
object Chinese{
  def main(args: Array[String]): Unit = {
    val c=new Chinese
    c.run()
  }
}
