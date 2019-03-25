package com.zhang.hadoop.scala

class Dog(val id: String) {

  var name: String = _

  def this (id:String, name: String) {
    this(id)
    this.name=name
  }
}

object Dog {

  def apply() = {
    println("apply invoked")
  }

  def apply(name: String): String = {
    println(name)
    name
  }

  def main(args: Array[String]): Unit = {
    val d = Dog()
    println(d)
    val dd = Dog("dog")
    println(dd)
    val ddd=new Dog("123","itcast")
    println(ddd.name)
  }
}
