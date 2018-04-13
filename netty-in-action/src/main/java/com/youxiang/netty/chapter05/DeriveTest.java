package com.youxiang.netty.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * 测试派生缓冲区与copy的区别
 * 派生缓冲区与源缓冲区具有相同的内容，所以对派生缓冲区的修改同时会修改源实例，而copy得到缓冲区是完全单独，对其修改
 * 不会影响源实例
 * @author: Rivers
 * @date: 2018/4/13
 */
public class DeriveTest {

    public static void main(String[] args) {
        ByteBuf src = Unpooled.copiedBuffer("Netty in action", CharsetUtil.UTF_8);
        System.out.println("src readerIndex is " + src.readerIndex());
        ByteBuf copyBuf = src.copy();
        copyBuf.setByte(0, 'B');
        System.out.println("src readerIndex is " + src.readerIndex() + ", byte of index 0 is " + (char) src.getByte(0));

        ByteBuf duplicateBuf = src.duplicate();
        duplicateBuf.setByte(0, 'B');
        System.out.println("src readerIndex is " + src.readerIndex() + ", byte of index 0 is " + (char) src.getByte(0));
    }
}
