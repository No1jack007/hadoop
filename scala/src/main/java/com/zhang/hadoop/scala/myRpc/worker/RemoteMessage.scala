package com.zhang.hadoop.scala.myRpc.worker

trait RemoteMessage extends Serializable

//Worker->Master
case class RegisterWorker(id: String, memory: Int, cores: Int) extends RemoteMessage

//Master->Worker
case class RegisteredWorker(masterUrl:String) extends RemoteMessage

//Worker->Master
case class Heartbeat(id:String)extends RemoteMessage

//Worker->self
case object SendHeratBeat

//Master->self
case object CheckTimeWorker