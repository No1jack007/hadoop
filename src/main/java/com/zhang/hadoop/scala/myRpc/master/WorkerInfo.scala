package com.zhang.hadoop.scala.myRpc.master

class WorkerInfo(val id:String,val memory:Int,val cores:Int) {

  var lastHeartbeatTime:Long=_

}
