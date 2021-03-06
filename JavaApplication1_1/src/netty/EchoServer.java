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
import io.netty.bootstrap.ServerBootstrap;
 
  import io.netty.handler.logging.LogLevel;
  import io.netty.handler.logging.LoggingHandler;
  import io.netty.handler.ssl.SslContext;
  import io.netty.handler.ssl.SslContextBuilder;
  import io.netty.handler.ssl.util.SelfSignedCertificate;
  import io.netty.bootstrap.*;
  import io.netty.channel.*;
  import io.netty.channel.nio.NioEventLoopGroup;
  import io.netty.channel.socket.SocketChannel;
  import io.netty.channel.socket.nio.NioServerSocketChannel;
  
  /**
34   * Echoes back any received data from a client.
35   */
  public final class EchoServer {
  
      static final boolean SSL = System.getProperty("ssl") != null;
      static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
  
      public static void main(String[] args) throws Exception {
         // Configure SSL.
          final SslContext sslCtx;
         if (SSL) {
             SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
          } else {
              sslCtx = null;
          }
  
          // Configure the server.
       //   EventLoopGroup bossGroup = new NioEventLoopGroup(1);
          EventLoopGroup workerGroup = new NioEventLoopGroup();
         try {
             ServerBootstrap b = new ServerBootstrap();
             b.group( workerGroup)
              .channel(NioServerSocketChannel.class)
              .option(ChannelOption.SO_BACKLOG, 100)
              .handler(new LoggingHandler(LogLevel.INFO))
              .childHandler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  public void initChannel(SocketChannel ch) throws Exception {
                       ChannelPipeline p = ch.pipeline();
                       if (sslCtx != null) {
                           p.addLast(sslCtx.newHandler(ch.alloc()));
                       }
                       //p.addLast(new LoggingHandler(LogLevel.INFO));
                      p.addLast(new EchoServerHandler());
                  }
               });
  
              // Start the server.
              ChannelFuture f = b.bind("127.0.0.1", PORT).sync();
 
              // Wait until the server socket is closed.
              f.channel().closeFuture().sync();
         } finally {
             // Shut down all event loops to terminate all threads.
           //  bossGroup.shutdownGracefully();
             //workerGroup.shutdownGracefully();
         }
      }
  }


