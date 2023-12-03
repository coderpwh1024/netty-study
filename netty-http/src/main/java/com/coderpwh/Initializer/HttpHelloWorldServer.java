package com.coderpwh.Initializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHelloWorldServer {


    private static final Logger logger = LoggerFactory.getLogger(HttpHelloWorldServer.class);


    static final int PORT = 8888;

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.childOption(ChannelOption.TCP_NODELAY, true);
            b.childOption(ChannelOption.SO_KEEPALIVE, true);
            b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpHelloWorldServerHandler());

            Channel ch = b.bind(PORT).sync().channel();
            logger.info("Netty http Server listening on port:{}", PORT);

            ch.closeFuture().sync();
        } catch (Exception e) {
            logger.error("异常为:{}", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

}
