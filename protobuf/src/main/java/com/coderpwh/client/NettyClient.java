package com.coderpwh.client;


import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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


    @PostConstruct
    public void init() {
        gorup = new NioEventLoopGroup();

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


}
