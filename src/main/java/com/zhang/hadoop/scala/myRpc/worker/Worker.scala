package com.zhang.hadoop.scala.myRpc.worker

import akka.actor.Actor

class Worker extends Actor{

  //建立连接
  override def preStart(): Unit = {
    context.actorSelection("akka.tcp://MasterSystem@127.0.0.1:88888/user/Master")
  }

  override def receive: Receive = {
    case "reply"=>{
      println("a reply from master")
    }
  }
}
