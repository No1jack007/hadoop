package com.zhang.hadoop.scala

import scala.collection.mutable.ListBuffer

/**
  * Created by zhang yufei on 2019/3/3.
  */
object Test {

  def main(args: Array[String]) {
    println("Hello, world!") // 输出 Hello World
    println(1/0.0)
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
    println(f1(1,2))

    val list=List(1,2,3)
    println("list")
    println(list(0))
    //可变list
    val lb=ListBuffer(1,2,3)
    lb(1)=200
    println(lb(1))
    println(lb.map(_*10))

    println("map,filter")
    val list2=lb.map(_*10)
    val list3=list2.filter(x=>x%2==0)
    println(list3)


    //拼接list
    println(0::list)
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
    println(list1 ::: list1)
    println(list1.::(7))

    println("group")
    val list4=list1.grouped(2)
    println(list4)
    for(i<-list4){
      println(i)
    }
    println(list4.toList)
  }

  def m1(a: Int, b: Int) = {
    a * b
  }

  var f1=(x: Int,y:Int)=>{
    x+y
  }


}
