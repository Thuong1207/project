/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netty;

  import io.netty.buffer.ByteBuf;
  import io.netty.buffer.Unpooled;
  import io.netty.channel.ChannelHandlerContext;
  import io.netty.channel.ChannelInboundHandlerAdapter;
  import javaapplication1.protobuffer.proto;
  import thuongdc.com.example.tutoria.AddressBookProtos;
  
  /**
   * Handler implementation for the echo client.  It initiates the ping-pong
   * traffic between the echo client and server by sending the first message to
   * the server.
   */
  public class EchoClientHandler extends ChannelInboundHandlerAdapter {
 
     private final ByteBuf firstMessage;
      public String protobuffer() {
        AddressBookProtos.Person.Builder person1 = AddressBookProtos.Person.newBuilder();
              person1.setId(12).setEmail("dasdasd").setName("fff").addPhone(AddressBookProtos.Person.PhoneNumber.newBuilder().setNumber("555-4321")
                      .setType(AddressBookProtos.Person.PhoneType.HOME)).build();
              person1.setEmail("ooo").build();
              person1.setId(1).build();
              person1.addPhone(AddressBookProtos.Person.PhoneNumber.newBuilder().setNumber("55667768686-4321")
                      .setType(AddressBookProtos.Person.PhoneType.HOME)).build();
              //System.err.println(person1.getId());
              //System.err.println(person1.getPhone(1));         
              return person1.getName();
        }
        //private ByteBuf protoBuf1 = Unpooled.wrappedBuffer(proto.protobuff_jetty().getBytes());
       // private ByteBuf protoBuf1 = Unpooled.wrappedBuffer(protobuffer().getBytes());
      
      /**
       * Creates a client-side handler.
      */
     public EchoClientHandler() {
         firstMessage = Unpooled.buffer(EchoClient.SIZE);
         for (int i = 0; i < firstMessage.capacity(); i ++) {
            firstMessage.writeByte((byte) i);
          }     
      }
  
      @Override
      public void channelActive(ChannelHandlerContext ctx) {
         ctx.writeAndFlush(firstMessage);
      }
 
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

