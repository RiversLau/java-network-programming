package com.youxiang.netty.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author: Rivers
 * @date: 2018/4/13
 */
public class ByteBufWriteTest {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in action", CharsetUtil.UTF_8);
        System.out.println("readerIndex=" + buf.readerIndex() + ", writerIndex=" + buf.writerIndex());
        ByteBuf dest = Unpooled.buffer(15);
        dest.writeBytes(buf);
        System.out.println("readerIndex=" + buf.readerIndex() + ", writerIndex=" + buf.writerIndex());
        System.out.println("dest readerIndex=" + dest.readerIndex() + ", writerIndex=" + dest.writerIndex());
    }
}
