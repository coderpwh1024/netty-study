package com.coderpwh.client;

import com.coderpwh.proto.UserMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@ChannelHandler.Sharable
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {


    @Autowired
    private NettyClient nettyClient;


    /***
     * 循环次数
     */
    private AtomicInteger fcount = new AtomicInteger();


    /***
     * 建立连接
     * @param context
     */
    @Override
    public void channelActive(ChannelHandlerContext context) {
        log.info("建立连接时:{}", new Date());
        context.fireChannelActive();
    }


    /**
     * 关闭连接
     *
     * @param context
     */
    @Override
    public void channelInactive(ChannelHandlerContext context) {

        try {
            log.info("关闭连接时:{}", new Date());
            final EventLoop eventLoop = context.channel().eventLoop();

            nettyClient.doConnect(new Bootstrap(), eventLoop);
            super.channelInactive(context);
        } catch (Exception e) {
            log.error("关闭连接异常:{}", e.getMessage());
        }
    }


    /***
     * 心跳请求处理
     * @param context
     * @param obj
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext context, Object obj) {
        log.info("循环请求时间:" + new Date() + ",次数" + fcount.get());
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;

            if (IdleState.WRITER_IDLE.equals(event.state())) {
                UserMsg.User.Builder userState = UserMsg.User.newBuilder().setState(2);
                context.channel().writeAndFlush(userState);
                fcount.getAndIncrement();
            }
        }

    }


    /***
     * 客户端业务逻辑处理
     * @param context
     * @param obj
     */
    @Override
    public void channelRead(ChannelHandlerContext context, Object obj) {

        if (!(obj instanceof UserMsg.User)) {
            log.info("未知数据,数据为:{}", obj);
            return;
        }

        try {
            UserMsg.User userMsg = (UserMsg.User) obj;
            log.info("客户端接受到的用户信息。编号:{},姓名:{},年龄:{}", userMsg.getId(), userMsg.getName(), userMsg.getAge());

            UserMsg.User.Builder userState = UserMsg.User.newBuilder().setState(1);
            context.writeAndFlush(userState);
            log.info("成功发送给客户端");
        } catch (Exception e) {
            log.error("异常信息为:{}", e.getMessage());
        } finally {
            ReferenceCountUtil.release(obj);
        }
    }


}
