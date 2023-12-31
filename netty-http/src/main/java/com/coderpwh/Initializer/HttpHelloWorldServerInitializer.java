package com.coderpwh.Initializer;

import io.netty.channel.ChannelInitializer;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;

public class HttpHelloWorldServerInitializer extends ChannelInitializer<SocketChannel> {


    /***
     * 初始化通道
     * @param socketChannel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new HttpServerCodec());


        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));

        pipeline.addLast(new HttpServerExpectContinueHandler());

         // TODO  待实现

//        pipeline.addLast()


    }

}
