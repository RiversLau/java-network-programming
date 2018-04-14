package com.youxiang.netty.chapter08;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.oio.OioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 不兼容的Channel与EventLoopGroup，导致启动过程报错，抛出IllegalStateException
 * @author: Rivers
 * @date: 2018/4/14
 */
public class BootstrapErrorSample {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(OioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("Received Data");
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("www.baidu.com", 80));
        channelFuture.syncUninterruptibly();
    }
}
