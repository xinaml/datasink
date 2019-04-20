package com.xinaml.datasink.netty.cilent;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: [lgq]
 * @Date: [19-4-20 下午2:08]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    //接受服务端发来的消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("return ： " + msg);
    }

    //与服务器建立连接
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
        String readline;
        readline = sin.readLine(); //等待输入
        while (!readline.equals("bye")) {
            System.out.println("Client send:" + readline);
            ctx.channel().writeAndFlush(readline);
           Object o  = ctx.channel().attr(AttributeKey.valueOf("a")).get();
            System.out.println("return："+o);
            readline = sin.readLine(); //等待输入
        } //继续循环
    }

    //与服务器断开连接
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }

    //异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭管道
        ctx.channel().close();
        //打印异常信息
        cause.printStackTrace();
    }

}
