package com.youxiang.netty.chapter11;

import com.google.protobuf.MessageLite;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * @author: Rivers
 * @date: 2018/4/15
 */
public class ProtoBufInitializer extends ChannelInitializer<Channel> {

    private final MessageLite messageLite;

    public ProtoBufInitializer(MessageLite messageLite) {
        this.messageLite = messageLite;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
        ch.pipeline().addLast(new ProtobufEncoder());
        ch.pipeline().addLast(new ProtobufDecoder(messageLite));
        ch.pipeline().addLast(new ObjectHandler());
    }

    private final static class ObjectHandler extends SimpleChannelInboundHandler<Object> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            //do something
        }
    }
}
