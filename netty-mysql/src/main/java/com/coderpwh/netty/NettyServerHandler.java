package com.coderpwh.netty;

import com.coderpwh.device.DeviceService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;


@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {


    private final DeviceService deviceService;


    public NettyServerHandler(DeviceService deviceService) {
        this.deviceService = deviceService;
    }


    /***
     *  连接
     * @param context
     * @throws UnknownHostException
     */
    @Override
    public void channelActive(ChannelHandlerContext context) throws UnknownHostException {
        context.write("Welcome to" + InetAddress.getLocalHost().getHostName() + "!\r\n");
        context.write("It is" + new Date() + "now.\r\n");
        context.flush();

    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }


}
