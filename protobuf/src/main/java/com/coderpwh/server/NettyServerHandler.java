package com.coderpwh.server;

import com.coderpwh.proto.UserMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
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


    /***
     *  通信链接
     * @param context
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception {
        log.info("链接的客户端地址:{}", context.channel().remoteAddress());

        UserMsg.User user = UserMsg.User.newBuilder().setId(1).setAge(24).setName("coderpwh").setState(0).build();
        context.writeAndFlush(user);
        super.channelActive(context);

    }


    /***
     * 超时处理
     * @param context
     * @param obj
     * @throws Exception
     */
    public void userEventTriggered(ChannelHandlerContext context, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;

            if (IdleState.READER_IDLE.equals(event.state())) {
                log.info("已经5秒没有接收到客户端的信息");
                if (idle_count.get() > 1) {
                    log.info("关闭这个不活跃的channel");
                    context.channel().close();
                }
                idle_count.getAndIncrement();
            }
        } else {
            super.userEventTriggered(context, obj);
        }
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
