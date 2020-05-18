package com.example.demo.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Classname MyClientHandle
 * @Description TODO
 * @Date 2020/5/16 18:40
 * @Created by SunFei
 */
public class MyClientHandle extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端："+ctx.channel().remoteAddress());
        ctx.channel().writeAndFlush("发送数据："+ UUID.randomUUID().toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);

        ctx.writeAndFlush("链接++++++++++++++++++");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("异常关闭");
        ctx.channel().close();
    }
}
