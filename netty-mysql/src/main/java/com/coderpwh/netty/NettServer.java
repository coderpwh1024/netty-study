package com.coderpwh.netty;

import com.coderpwh.device.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "netty")
@Slf4j
public class NettServer {


    @Value("${netty.bind_address}")
    private String host;

    @Value("${netty.bind_port}")
    private Integer port;


    @Value("${netty.boss_group_thread_count}")
    private Integer bossGroupThreadCount;

    @Value("${netty.worker_group_thread_count}")
    private Integer workerGroupThreadCount;


    @Autowired
    private DeviceService deviceService;


}
