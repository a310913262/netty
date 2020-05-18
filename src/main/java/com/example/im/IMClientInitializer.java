package com.example.im;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.Socket;

/**
 * @Classname IMClientInitializer
 * @Description TODO
 * @Date 2020/5/16 19:29
 * @Created by SunFei
 */
public class IMClientInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //        分隔符解码

        pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
//字符解码
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
//        字符加密
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
//        自定义处理器
        pipeline.addLast(new IMClientchannel());
    }
}
