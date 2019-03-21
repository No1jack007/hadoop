package com.zhang.hadoop.scala

class People(val id:String,var name:String) {

}

object People{
  def main(args: Array[String]): Unit = {
      val p=new People("123","abc")
      println(p.id)
      p.name="itcast"
      println(p.name)
  }
}
