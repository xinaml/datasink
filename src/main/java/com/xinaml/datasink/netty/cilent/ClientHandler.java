package com.xinaml.datasink.netty.cilent;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: [lgq]
 * @Date: [19-4-22 下午2:54]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext cxt, String msg)
            throws Exception {
        //客户端主要用来接收服务器发送的消息
        System.out.println("客户端接收到的服务端消息："+msg);
    }

}
