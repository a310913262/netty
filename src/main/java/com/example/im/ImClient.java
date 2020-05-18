package com.example.im;

import com.example.demo.client.ClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Classname ImClient
 * @Description TODO
 * @Date 2020/5/16 19:27
 * @Created by SunFei
 */
public class ImClient {

//    聊天室客户端
    public static void main(String[] args) {
        System.out.println("**************************CLIENT  START**************************");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new IMClientInitializer());
//            ChannelFuture sync = bootstrap.connect("127.0.0.1",8080).sync();
//            sync.channel().closeFuture().sync();
            Channel channel = bootstrap.connect("127.0.0.1", 8080).sync().channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                channel.writeAndFlush(bufferedReader);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
