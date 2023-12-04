package com.coderpwh.server;

import com.coderpwh.proto.UserMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
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

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
        log.info("第" + count.get() + "次" + "，服务端接受消息:{}", msg);
        try {
            if (msg instanceof UserMsg.User) {
                UserMsg.User user = (UserMsg.User) msg;
                if (user.getState() == 1) {
                    log.info("客户端业务处理成功");
                } else if (user.getState() == 2) {
                    log.info("接受到客户端的发送的心跳！");
                } else {
                    log.info("未知数据!");
                }
            } else {
                log.info("未知数据!,数据为:{}", msg);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ;
        } finally {
            ReferenceCountUtil.release(msg);
        }
        count.getAndIncrement();

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
