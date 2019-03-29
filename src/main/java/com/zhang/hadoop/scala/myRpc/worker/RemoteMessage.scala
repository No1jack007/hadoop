package com.zhang.hadoop.scala.myRpc.worker

trait RemoteMessage extends Serializable

//Worker->Master
case class RegisterWorker(id:String,memory:Int,cores:Int)