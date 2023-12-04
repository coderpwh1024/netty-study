package com.coderpwh.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    /***
     * 空闲次数
     */
    private AtomicInteger idle_count = new AtomicInteger(1);


    /**
     * 发送次数
     */
    private AtomicInteger count = new AtomicInteger(1);


    public void channelActive(ChannelHandlerContext context) {
        log.info("链接的客户端地址:{}", context.channel().remoteAddress());

//        UserMsg.User user =

    }


}
