package com.coderpwh.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("nettyClient")
public class NettyClient {


    @Value("${server.bind_address}")
    private String host;


    @Value("${server.bind_port}")
    private Integer port;


    private boolean initFalg = true;

    private EventLoopGroup gorup;

    private ChannelFuture channelFuture;


    /***
     * 初始化
     *  客户端: Bootstrap
     *  服务端: ServerBootstrap
     */
    @PostConstruct
    public void init() {
        gorup = new NioEventLoopGroup();
        doConnect(new Bootstrap(), gorup);

    }


    /***
     * 停止服务
     */
    @PreDestroy
    public void shutdown() {
        log.info("正在停止客户端");
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("异常信息为:{}", e.getMessage());
        } finally {
            gorup.shutdownGracefully();
        }
        log.info("客户端已经停止了！");
    }


    /***
     * 重新连接
     */
    public void doConnect(Bootstrap bootstrap, EventLoopGroup eventLoopGroup) {
        try {
            if (bootstrap != null) {
                bootstrap.group(eventLoopGroup);
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
                bootstrap.handler(new NettyClientInitializer());
                bootstrap.remoteAddress(host, port);

                channelFuture = bootstrap.connect().addListener((ChannelFuture futureListener) -> {
                    final EventLoop eventLoop = futureListener.channel().eventLoop();
                    if (!futureListener.isSuccess()) {
                        log.info("与服务端断开连接!在10s之后准备尝试重连");
                        eventLoop.schedule(() -> doConnect(new Bootstrap(), eventLoop), 10, TimeUnit.SECONDS);
                    }
                });

                if (initFalg) {
                    log.info("Netty客户端启动成功");
                    initFalg = false;
                }
            }

        } catch (Exception e) {
            log.error("客户端连接失败!，异常为:{}", e.getMessage());
        }

    }


}
