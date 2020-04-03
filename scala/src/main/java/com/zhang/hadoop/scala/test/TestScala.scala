package com.zhang.hadoop.scala.test

import scala.util.Random

object TestScala {

  def main(args: Array[String]): Unit = {


    val listA = List(1,2,3)

    println(listA.foldLeft(0)((sum,i)=>sum+i))


    val l1=List(1,2,3)
    val l2=List(1,2,3)
    println(l1==l2)

    val names=Array("Akiho Yoshizawa","YuiHatano","Aoi Sola")
    val name=names(Random.nextInt(names.length))

    name match {
      case "Akiho Yoshizawa" => println("吉老师。。。")
      case "YuiHatano"=> println("波老师。。。")
      case _ => println("真不知道你们在说什么")
    }



  }

}
