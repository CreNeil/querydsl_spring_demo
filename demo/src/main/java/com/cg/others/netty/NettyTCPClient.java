package com.cg.others.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class NettyTCPClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap clientBootstrap = new Bootstrap();

            clientBootstrap.group(group);
            clientBootstrap.channel(NioSocketChannel.class);
            clientBootstrap.remoteAddress(new InetSocketAddress("localhost", 9999));
            clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ClientHandler());
                }
            });
            ChannelFuture channelFuture = clientBootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    static class ClientHandler extends SimpleChannelInboundHandler {

        @Override
        public void channelActive(ChannelHandlerContext channelHandlerContext) {
            channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks! popo", CharsetUtil.UTF_8));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
            cause.printStackTrace();
            channelHandlerContext.close();
        }

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
            System.out.println("Client received: " + ((ByteBuf) o).toString(CharsetUtil.UTF_8));
        }
    }
}
