package com.example.im;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @Classname IMServerchannel
 * @Description TODO
 * @Date 2020/5/16 21:42
 * @Created by SunFei
 */
public class IMClientchannel extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg){
        System.out.println(msg);
    }

}
