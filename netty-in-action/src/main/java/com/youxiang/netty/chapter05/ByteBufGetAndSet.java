package com.youxiang.netty.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author: Rivers
 * @date: 2018/4/13
 */
public class ByteBufGetAndSet {

    public static void main(String[] args) {

        ByteBuf buf = Unpooled.copiedBuffer("Netty in action", CharsetUtil.UTF_8);
        System.out.println((char)buf.getByte(0));
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.setByte(0, 'B');
        System.out.println(buf.getBoolean(0));
        assert readerIndex == buf.readerIndex();
        assert writerIndex == buf.writerIndex();
    }
}
