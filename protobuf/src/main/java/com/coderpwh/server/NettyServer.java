package com.coderpwh.server;


import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
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
