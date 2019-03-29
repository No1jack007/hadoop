package com.zhang.hadoop.scala.base

import scala.io.Source


class MissRight {

  //主构造器
  //在new的时候主构造器里的内容会先被执行
  val name="abc"
  println(name)

  def sayHi={
    println("hi~")
  }

  //读取文件
  try {
    val source=Source.fromFile("D:\\0-program\\test\\123.txt").mkString
    println(source)
  }catch {
    case e:Exception=>e.printStackTrace()
  }finally {
    println("finally")
  }

}

object MissRight{
  def main(args: Array[String]): Unit = {
    var m=new MissRight
  }
}