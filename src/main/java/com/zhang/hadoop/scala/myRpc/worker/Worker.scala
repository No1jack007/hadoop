package com.zhang.hadoop.scala.myRpc.worker

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class Worker(val masterHost: String, val masterPort: String) extends Actor {

  var master: ActorSelection = _

  //建立连接
  override def preStart(): Unit = {
    master = context.actorSelection(s"akka.tcp://MasterSystem@$masterHost:$masterPort/user/Master")
    master ! "connect"
  }

  override def receive: Receive = {
    case "reply" => {
      println("a reply from master")
    }
    case "connect"=>{
      println("a client connected Worker")
    }
  }
}

object Worker {
  def main(args: Array[String]): Unit = {
    val host = "127.0.0.1"
    val port = "9999"
    //准备配置
    val configStr =
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname="$host"
         |akka.remote.netty.tcp.port="$port"
      """.stripMargin
    val config = ConfigFactory.parseString(configStr)
    //ActorSystem老大，负责创建和监控下面的Actor，他是单例的
    val actorSystem = ActorSystem("WorkerSystem", config)
    val worker=actorSystem.actorOf(Props(new Worker("127.0.0.1", "8888")), "Worker")
    worker ! "connect"
//    actorSystem.terminate()

  }
}
