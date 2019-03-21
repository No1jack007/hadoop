package com.zhang.hadoop.scala

/**
  * Created by zhang yufei on 2019/3/3.
  */
object Test {

  def main(args: Array[String]) {
    println("Hello, world!") // 输出 Hello World
    for (i <- 1 to 3) {
      println(i);
    }
    for (i <- 1 until 3) {
      println(i);
    }
    println(1 - (10));
    println(m1(2, 3))
    val r = m1(3, 4)
    println(r)
    print(f1(1,2))

    val pair = (99, "Luftballons")
    println(pair._1)
    println(pair._2)


    println("list")
    var list1=List(1,2,3,4,5)
    println(0::list1)
    println(list1.::(0))
    println(0+:list1)
    println(list1.+:(0))
    println(list1:+0)
    println(list1)
  }

  def m1(a: Int, b: Int) = {
    a * b
  }

  var f1=(x: Int,y:Int)=>{
    x+y
  }


}
