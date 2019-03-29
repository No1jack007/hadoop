package com.zhang.hadoop.scala.myRpc.master

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import com.zhang.hadoop.scala.myRpc.worker.RegisterWorker

import scala.collection.mutable


class Master extends Actor{

  println("constructor invoked")

  val idToWorker=new mutable.HashMap[String,WorkerInfo]()

  override def preStart(): Unit = {
    println("preStart invoked")
  }

  //用于接收消息
  override def receive: Receive = {
    case "connect"=>{
      println("a client connected")
      sender ! "reply"
    }
    case "hello"=>{
      println("hello")
    }
    case RegisterWorker(id,memory,cores)=>{
      if(!idToWorker.contains(id)){
        val workerInfo=new WorkerInfo(id,memory,cores)
        idToWorker(id)=workerInfo
      }
    }
  }
}

object Master{
  def main(args: Array[String]): Unit = {
    val host="127.0.0.1"
    val port="8888"
    //准备配置
    val configStr=
      s"""
        |akka.actor.provider="akka.remote.RemoteActorRefProvider"
        |akka.remote.netty.tcp.hostname="$host"
        |akka.remote.netty.tcp.port="$port"
      """.stripMargin
    val config=ConfigFactory.parseString(configStr)
    //ActorSystem老大，负责创建和监控下面的Actor，他是单例的
    val actorSystem=ActorSystem("MasterSystem",config)
    //创建Actor
    val master=actorSystem.actorOf(Props(new Master),"Master")
    master !"hello"
//    actorSystem.terminate()
  }
}
