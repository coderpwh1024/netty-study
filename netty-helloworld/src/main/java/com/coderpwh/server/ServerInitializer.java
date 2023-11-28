package com.coderpwh.server;

import com.sun.corba.se.spi.activation.ServerHelper;
import com.sun.xml.internal.ws.message.StringHeader;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private static final StringDecoder DECODER = new StringDecoder();

    private static final StringDecoder ENCODER = new StringDecoder();

//    private static final ServerHandler SERVER_HANDLER = new ServerHandler();


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

    }


}
