package com.zhang.hadoop.scala

class Person {

  //val类型不可修改
  val id="9527"

  var name="华安"

  private[this] var pop:String=_

  def printPop={
    println(pop)
  }

}

object Person{
  def main(args: Array[String]): Unit = {
    val p=new Person
    println(p.id+"  "+p.name)

    p.name="唐伯虎"

    println(p.id+"  "+p.name)

    println(p.printPop)
  }
}