package com.example.im;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Classname IMServerchannel
 * @Description TODO
 * @Date 2020/5/16 21:42
 * @Created by SunFei
 */
public class IMServerchannel extends SimpleChannelInboundHandler<String> {

    private  static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("read:"+msg);
        Channel channel = ctx.channel();
        group.forEach(ch ->{
            if (channel != ch ){
                ch.writeAndFlush("[客户]"+channel.id().asShortText()+"发送了消息--"+msg+"\r\n");
            }
            else {
                ch.writeAndFlush("[自己]"+"发送了消息"+msg+"\r\n");
            }
        });

    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);

        String channelId = ctx.channel().id().asShortText();
//        IMCacheMap.map.put(channelId,"")
        System.out.println("ADD:"+channelId);
//        先广播，不发送给当前channel
        group.writeAndFlush(ctx.channel().remoteAddress()+"：广播加入\r\n");
        group.add(ctx.channel());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("Registered:"+ctx.channel().id().asShortText());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
//        表示连接处于活动状态
        System.out.println("上线了:"+ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
