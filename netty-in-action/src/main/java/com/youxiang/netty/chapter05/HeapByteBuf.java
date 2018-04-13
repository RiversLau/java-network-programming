package com.youxiang.netty.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author: Rivers
 * @date: 2018/4/13
 */
public class HeapByteBuf {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in action", CharsetUtil.UTF_8);
        if (buf.hasArray()) {
            byte[] array = buf.array();
            int offset = buf.arrayOffset() + buf.readerIndex();
            int length = buf.readableBytes();
            System.out.println("Array length is " + array.length + ", offset is " + offset + ", readable bytes length is " + length);
        }
    }
}
