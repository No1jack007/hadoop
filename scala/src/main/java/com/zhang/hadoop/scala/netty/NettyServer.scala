package com.zhang.hadoop.scala.netty

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.serialization.{ClassResolvers, ObjectDecoder, ObjectEncoder}

class NettyServer {

  def bind(host: String, port: Int): Unit = {
    //配置服务端线程池组
    //用于服务器接收客户端的连接，参数默认和cpu核数一致，可配置
    val bossGroup = new NioEventLoopGroup(4)
    //用户进行SocketChannel网络读写
    val workerGroup = new NioEventLoopGroup()

    try {
      val bootstrap = new ServerBootstrap()
      bootstrap.group(bossGroup, workerGroup)
        .channel(classOf[NioServerSocketChannel])
        .childHandler(new ChannelInitializer[SocketChannel] {
          override def initChannel(ch: SocketChannel): Unit = {
            ch.pipeline().addLast(
              new ObjectEncoder,
              new ObjectDecoder(ClassResolvers.cacheDisabled(getClass.getClassLoader)),
              new ServerHandler
            )
          }
        })
      //绑定端口，调用sync方法等待绑定操作完成
      val channelFuture = bootstrap.bind(host, port).sync()
      //等待服务关闭
      channelFuture.channel().closeFuture().sync()
    } finally {
      //优雅的退出，释放线程吃资源
      bossGroup.shutdownGracefully()
      workerGroup.shutdownGracefully()
    }
  }
}

object NettyServer {

  def main(args: Array[String]): Unit = {
    //    val host = args(0)
    //    val port = args(1).toInt
    val host = "127.0.0.1"
    val port = 8001
    val server = new NettyServer
    server.bind(host, port)
  }

}

