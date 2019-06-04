package com.zhang.hadoop.spark

object TestHash {

  def main(args: Array[String]): Unit = {
    val key="java.itcast.cn"
    val keyHash=key.hashCode
    val mod=3
    val rawMod=keyHash % mod
    val partNum=rawMod+(if(rawMod<0) mod else 0)
    println(partNum)
  }

}
