package com.youxiang.netty.chapter10;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * @author: Rivers
 * @date: 2018/4/15
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {

    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
