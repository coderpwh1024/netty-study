package com.coderpwh.netty;

import com.alibaba.fastjson.JSONObject;
import com.coderpwh.device.DeviceService;
import com.coderpwh.domain.Device;
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


    /***
     *  业务处理
     * @param context
     * @param request
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext context, String request) throws Exception {
        try {
            JSONObject jsonObject = JSONObject.parseObject(request);
            Device device = jsonObject.toJavaObject(Device.class);
            deviceService.saveDevice(device);
            context.write("Successfully saved!\r\n");
        } catch (Exception e) {
            context.write("error json format!\r\n");
            e.printStackTrace();
        }


    }


    /***
     * 刷新netty连接
     * @param context
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        context.flush();

    }


    /***
     * 异常处理
     * @param context
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }


}
