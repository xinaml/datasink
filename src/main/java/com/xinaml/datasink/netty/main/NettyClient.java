package com.xinaml.datasink.netty.main;


import com.xinaml.datasink.netty.cilent.ClientInitializerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: [lgq]
 * @Date: [19-4-20 下午2:06]
 * @Description:
 * @Version: [1.0.0]
 */
public class NettyClient {
    private String host;
    private int port;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws IOException {
        EventLoopGroup worker = new NioEventLoopGroup();  //设置一个worker线程，使用
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker);
        bootstrap.channel(NioSocketChannel.class); //指定所使用的 NIO 传输 Channel
        bootstrap.handler(new ClientInitializerHandler());//处理器

        try {
            //使用指定的 端口设置套 接字地址
            Channel channel = bootstrap.connect(host, port).sync().channel();
            while (true) {
                //向服务端发送内容
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));
                String input = reader.readLine();
                if (input != null) {
                    if ("quit".equals(input)) {
                        System.exit(1);
                    }
                    channel.writeAndFlush(input);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
//            worker.shutdownGracefully().sync();
            System.exit(1);
        }
    }


    public static void main(String[] args)throws Exception {
        new NettyClient("127.0.0.1",7000).run();
    }
}
