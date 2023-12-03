package com.coderpwh.Initializer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.AsciiString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHelloWorldServerHandler extends SimpleChannelInboundHandler<HttpObject> {


    private static final Logger logger = LoggerFactory.getLogger(HttpHelloWorldServerHandler.class);


    private HttpHeaders headers;

    private HttpRequest request;

    private FullHttpRequest fullHttpRequest;


    private static final String FAVICON_ICO = "/favicon.ico";

    private static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");

    private static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");

    private static final AsciiString CONNECTION = AsciiString.cached("Connection");

    private static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

    }
}
