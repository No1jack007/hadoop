package com.zhang.hadoop.scala.netty

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.serialization.{ClassResolver, ClassResolvers, ObjectDecoder, ObjectEncoder}


class NettyClient{

  def connect(host:String,port:Int):Unit={
    //创建客户端NIO线程组
    val eventGroup=new NioEventLoopGroup()
    //创建客户端辅助启动lei
    val bootstrap=new Bootstrap
    try{
      bootstrap.group(eventGroup)
        .channel(classOf[NioSocketChannel])
        .handler(new ChannelInitializer[SocketChannel] {
          override def initChannel(ch: SocketChannel): Unit = {
            ch.pipeline().addLast(
              new ObjectEncoder,
              new ObjectDecoder(ClassResolvers.cacheDisabled(getClass.getClassLoader)),
              new ClientHandler
            )
          }
        })
      val channelFuture=bootstrap.connect(host,port).sync()
    }finally {
      eventGroup.shutdownGracefully()
    }


  }

}


object NettyClient {
  def main(args: Array[String]): Unit = {
    //    val host = args(0)
    //    val port = args(1).toInt
    val host = "127.0.0.1"
    val port =8001
    val client = new NettyClient
    client.connect(host, port)
  }
}
