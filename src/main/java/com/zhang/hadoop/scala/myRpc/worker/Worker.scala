package com.zhang.hadoop.scala.myRpc.worker

import java.util.UUID
import java.util.concurrent.Executors

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class Worker(val masterHost: String, val masterPort: String, val memeory: Int, val cores: Int) extends Actor {

  var master: ActorSelection = _
  val workerId = UUID.randomUUID().toString
  val CHECK_INTERVAL=10000

  //建立连接
  override def preStart(): Unit = {
    master = context.actorSelection(s"akka.tcp://MasterSystem@$masterHost:$masterPort/user/Master")
    master ! "connect"
    master ! RegisterWorker(workerId, memeory, cores)
  }

  override def receive: Receive = {
    case "reply" => {
      println("a reply from master")
    }
    case "connect" => {
      println("a client connected Worker")
    }
    case RegisteredWorker(masterUrl)=>{
      println(masterUrl)
      //启动定时器发送心跳
      try {
//        context.system.scheduler.scheduleOnce(5 seconds, self, "123")
        context.system.scheduler.schedule(1 millis, CHECK_INTERVAL millis, self, SendHeratBeat)(ExecutionContext.fromExecutor(Executors.newFixedThreadPool(1)),self)
      }catch {
          case ex: Exception =>{
            println(ex)
          }
      }
    }
    case SendHeratBeat=>{
      master ! Heartbeat(workerId)
    }
  }
}

object Worker {
  def main(args: Array[String]): Unit = {
    val host = "127.0.0.1"
    val port = "9999"
    val masterHost = "127.0.0.1"
    val masterort = "8888"
    val memory = 1024
    val cores = 4
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
    val worker = actorSystem.actorOf(Props(new Worker(masterHost, masterort, memory, cores)), "Worker")
    worker ! "connect"
    //actorSystem.terminate()

  }
}
