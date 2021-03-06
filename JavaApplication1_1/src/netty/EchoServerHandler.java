/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netty;

/**
 *
 * @author thuongdc
 */
import io.netty.channel.ChannelHandler.Sharable;
  import io.netty.channel.ChannelHandlerContext; 
import io.netty.channel.ChannelInboundHandlerAdapter;
  /**
23   * Handler implementation for the echo server.
24   */
 @Sharable
 public class EchoServerHandler extends ChannelInboundHandlerAdapter {
  
     @Override
     public void channelRead(ChannelHandlerContext ctx, Object msg) {
         ctx.write(msg);
     }
 
      @Override
      public void channelReadComplete(ChannelHandlerContext ctx) {
         ctx.flush();
      }
  
     @Override
     public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
         // Close the connection when an exception is raised.
          cause.printStackTrace();
          ctx.close();
     }
  }
