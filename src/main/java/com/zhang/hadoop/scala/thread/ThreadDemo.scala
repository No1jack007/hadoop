package com.zhang.hadoop.scala.thread

import java.util.concurrent.{Callable, Executor, Executors, Future}

class ThreadDemo {

}

object ThreadDemo {

  def main(args: Array[String]): Unit = {
    val pool = Executors.newFixedThreadPool(5)
    for (i <- 1 to 10) {
      pool.execute(new Runnable {
        override def run(): Unit = {
          println(Thread.currentThread().getName)
          Thread.sleep(1000)
        }
      })
    }
    val f:Future[Int]=pool.submit(new Callable[Int] {
      override def call(): Int = {
        Thread.sleep(30000)
        100
      }
    })
    var status=f.isDone
    println("task status:"+status)
    if(!status){
      Thread.sleep(15000)
    }
    status=f.isDone
    println("task status:"+status)
    if(status){
      println(f.get())
    }
  }

}
