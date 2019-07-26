package com.zhang.hadoop.scala.netty

import io.netty.buffer.{ByteBuf, Unpooled}
import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}

class ServerHandler extends ChannelInboundHandlerAdapter{

  //有客户端连接后调用
  override def channelActive(ctx: ChannelHandlerContext): Unit = {
//    super.channelActive(ctx)
    println("channelActive invoked")
  }

  //接收客户端发送来的消息
  override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
//    super.channelRead(ctx, msg)
    val byteBuf=msg.asInstanceOf[ByteBuf]
    val bytes=new Array[Byte](byteBuf.readableBytes())
    byteBuf.readBytes(bytes)
    val message=new String(bytes,"UTF-8")
    println(message)
    val back="Good Boy!"
    val resp=Unpooled.copiedBuffer(back.getBytes("UTF-8"))
    ctx.write(resp)
  }

  //将消息队列中的数据写到SocketChannel并发送给对方
  override def channelReadComplete(ctx: ChannelHandlerContext): Unit = {
//    super.channelReadComplete(ctx)
    println("channelReadComplete invoked")
    ctx.flush()
  }

  //发生异常是关闭ChannelHandlerContext
  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
//    super.exceptionCaught(ctx, cause)
    println("exceptionCaught invoked")
    ctx.close()
  }



}
