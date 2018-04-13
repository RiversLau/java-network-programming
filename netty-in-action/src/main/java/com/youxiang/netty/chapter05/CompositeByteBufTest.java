package com.youxiang.netty.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author: Rivers
 * @date: 2018/4/13
 */
public class CompositeByteBufTest {

    public static void main(String[] args) {
        CompositeByteBuf buf = Unpooled.compositeBuffer();
        ByteBuf headerBuf = Unpooled.copiedBuffer("HTTP/1.1 200 OK", CharsetUtil.UTF_8);
        ByteBuf bodyBuf = Unpooled.directBuffer();
        bodyBuf.writeBytes("Netty in action".getBytes(CharsetUtil.UTF_8));
        buf.addComponents(headerBuf, bodyBuf);

        buf.removeComponent(0);
        for (ByteBuf temp : buf) {
            System.out.println(temp.toString());
        }
    }
}
