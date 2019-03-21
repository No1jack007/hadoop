package com.zhang.hadoop.scala

class People(val id:String,var name:String,var age:Int=18) {

}

object People{
  def main(args: Array[String]): Unit = {
      val p=new People("123","abc",20)
      println(p.id)
      p.name="itcast"
      println(p.name)
      println(p.age)
  }
}
