package com.youxiang.netty.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author: Rivers
 * @date: 2018/4/13
 */
public class DirectByteBuf {

    public static void main(String[] args) {
        ByteBuf buf = Unpooled.directBuffer();
        buf.writeBytes("Netty in action".getBytes(CharsetUtil.UTF_8));
        if (!buf.hasArray()) {
            int length = buf.readableBytes();
            byte[] array = new byte[length];
            buf.getBytes(buf.readerIndex(), array);
            System.out.println(array);
        }
    }
}
