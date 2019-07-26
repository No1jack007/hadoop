package com.zhang.hadoop.scala.netty

import io.netty.buffer.{ByteBuf, Unpooled}
import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}

class ClientHandler extends ChannelInboundHandlerAdapter{

  //有客户端建立连接后调用
  override def channelActive(ctx: ChannelHandlerContext): Unit = {
//    super.channelActive(ctx)
    println("channelActive")
    val content="hello server"
    ctx.writeAndFlush(Unpooled.copiedBuffer(content.getBytes("UTF-8")))
//    ctx.writeAndFlush(RegiesterMsg("hello server"))
  }

  //接收客户端发送来的消息
  override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
//    super.channelRead(ctx, msg)
    println("channelRead")
    val byteBuf=msg.asInstanceOf[ByteBuf]
    val bytes=new Array[Byte](byteBuf.readableBytes())
    byteBuf.readBytes(bytes)
    val message=new String(bytes,"UTF-8")
    println(message)
  }

  //将消息队列中的数据写到SocketChannel并发送给对方
  override def channelReadComplete(ctx: ChannelHandlerContext): Unit = {
//    super.channelReadComplete(ctx)
    println("channelReadComplete")
    ctx.flush()
  }

  //发生异常是关闭ChannelHandlerContext
  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
//    super.exceptionCaught(ctx, cause)
    println("exceptionCaught")
    ctx.close()
  }



}
