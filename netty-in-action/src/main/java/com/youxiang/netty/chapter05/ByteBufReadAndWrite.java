package com.youxiang.netty.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author: Rivers
 * @date: 2018/4/13
 */
public class ByteBufReadAndWrite {

    public static void main(String[] args) {

        ByteBuf buf = Unpooled.copiedBuffer("Netty in action", CharsetUtil.UTF_8);
        System.out.println("initial readerIndex is : " + buf.readerIndex());
        System.out.println("initial writerIndex is : " + buf.writerIndex());

        System.out.println((char) buf.readByte());
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        System.out.println("After read a byte readerIndex is : " + readerIndex + ", wirterIndex is : " + writerIndex);

        buf.writeByte('B');
        System.out.println("After write a byte readerIndex is : " + buf.readerIndex() + ", writerIndex is : " + buf.writerIndex());
    }
}
