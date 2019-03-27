package com.zhang.hadoop.scala.myRpc.master

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory


class Master extends Actor{

  println("constructor invoked")

  override def preStart(): Unit = {
    println("preStart invoked")
  }

  //用于接收消息
  override def receive: Receive = {
    case "connect"=>{
      println("a client connected")
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
    actorSystem.terminate()
  }
}
