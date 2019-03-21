package com.zhang.hadoop.scala

class SingletonDemo {

}
//单例对象，一个进程里面只有一份
object SingletonDemo{
  def main(args: Array[String]): Unit = {
    val s=SingletonDemo
    println(s)
  }
}
