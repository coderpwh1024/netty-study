package com.coderpwh.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service("nettyServer")
@Slf4j
public class NettyServer {


    @Value("${server.bind_port}")
    private Integer port;

    @Value("${server.netty.boss_group_thread_count}")
    private Integer bossGroupThreadCount;

    @Value("${server.netty.worker_group_thread_count}")
    private Integer workerGroupThreadCount;

    @Value("${server.netty.leak_detector_level}")
    private String leakDatectorLevel;

    @Value("${server.netty.max_payload_size}")
    private Integer maxPayloadSize;

    private ChannelFuture channelFuture;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;


    public void init() throws InterruptedException {
        log.info("Setting resource leak detector level to {}", leakDatectorLevel);
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.valueOf(leakDatectorLevel.toUpperCase()));

        log.info("Starting Server");

        bossGroup = new NioEventLoopGroup(bossGroupThreadCount);
        workerGroup = new NioEventLoopGroup(workerGroupThreadCount);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new NettyServerInitializer());

        channelFuture = bootstrap.bind(port).sync();

        log.info("Server started");
    }


    @PreDestroy
    public void shutdown() {
        log.info("Stopping Server");
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
        log.info("server stopped!");
    }

}
