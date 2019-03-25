package com.zhang.hadoop.scala

class Cat {

  var name:String=_

  def this(name:String){
    this()
  }
}

object Cat{
  def main(args: Array[String]): Unit = {
    val c=new Cat("it")
    println(c.name)
  }
}
