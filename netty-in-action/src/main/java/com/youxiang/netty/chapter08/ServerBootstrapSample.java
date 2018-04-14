package com.youxiang.netty.chapter08;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author: Rivers
 * @date: 2018/4/14
 */
public class ServerBootstrapSample {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        server.group(group).channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("Received Data");
                    }
                });
        ChannelFuture channelFuture = server.bind(new InetSocketAddress(8080));
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                System.out.println("Server bound");
            } else {
                System.err.println("Bound attempt failed");
                future.cause().printStackTrace();
            }
        });
    }
}
