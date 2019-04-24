package com.zhang.hadoop.scala.implic

class ImplicitValue {

}

object Context{
  implicit val a="lao zhao"
  implicit val aa=123
}

object ImplicitValue {

  def sayHi()(implicit name:String="lao duan"): Unit = {
    println(s"hi $name")
  }

  def main(args: Array[String]): Unit = {
    import Context._
    sayHi()
  }

}


