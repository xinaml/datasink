package com.xinaml.datasink.netty.cilent;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;

/**
 * @Author: [lgq]
 * @Date: [19-4-20 下午2:06]
 * @Description:
 * @Version: [1.0.0]
 */
public class NettyClient {


    public static void main(String[] args) {
        //worker负责读写数据
        EventLoopGroup worker = new NioEventLoopGroup();
        ChannelFuture futrue = null;
        try {
            //辅助启动类
            Bootstrap bootstrap = new Bootstrap();

            //设置线程池
            bootstrap.group(worker);

            //设置socket工厂
            bootstrap.channel(NioSocketChannel.class);

            //设置管道
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //获取管道
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    //字符串解码器
                    pipeline.addLast(new StringDecoder());
                    //字符串编码器
                    pipeline.addLast(new StringEncoder());
                    //处理类
                    pipeline.addLast(new ClientHandler());
                    pipeline.addLast(new ReadTimeoutHandler(60));//设置超时时间

                }
            });

            //发起异步连接操作
             futrue = bootstrap.connect(new InetSocketAddress("127.0.0.1", 7000)).sync();
            //等待客户端链路关闭
        } catch (InterruptedException e) {
            //优雅的退出，释放NIO线程组
            worker.shutdownGracefully();
            try {
                futrue.channel().closeFuture().sync();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
