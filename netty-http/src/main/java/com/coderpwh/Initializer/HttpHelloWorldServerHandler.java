package com.coderpwh.Initializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.coderpwh.entity.User;
import com.coderpwh.service.impl.SerializerServiceImpl;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

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
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        User user = new User();
        user.setUserName("coderpwh");
        user.setDate(new Date());

        if (msg instanceof HttpRequest) {
            request = (HttpRequest) msg;
            headers = request.headers();
            String uri = request.uri();

            logger.info("http uri:" + uri);

            if (uri.equals(FAVICON_ICO)) {
                return;
            }

            HttpMethod method = request.method();

            if (method.equals(HttpMethod.GET)) {
                QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8));
                Map<String, List<String>> uriAttributes = queryStringDecoder.parameters();

                for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
                    for (String attrVal : attr.getValue()) {
                        logger.info(attr.getKey() + "=" + attrVal);
                    }
                }
                user.setMethod("get");
            } else if (method.equals(HttpMethod.POST)) {

                fullHttpRequest = (FullHttpRequest) msg;
                dealWithContentType();
                user.setMethod("post");
            }

            SerializerServiceImpl jsonSerializer = new SerializerServiceImpl();
            byte[] content = jsonSerializer.serialize(user);

            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content));
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());

            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if (!keepAlive) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, KEEP_ALIVE);
                ctx.write(response);
            }

        }


    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.close();
    }


    /***
     * 处理常用的Content-type
     */
    public void dealWithContentType() {
        String contentType = getContentType();

        if (contentType.equals("application/json")) {
            String jsonStr = fullHttpRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
            JSONObject obj = JSON.parseObject(jsonStr);

            for (Map.Entry<String, Object> item : obj.entrySet()) {
                logger.info(item.getKey() + "=" + item.getValue().toString());
            }
        } else if (contentType.equals("application/x-www-form-urlencoded")) {
            //方式一：使用 QueryStringDecoder
            String jsonStr = fullHttpRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
            QueryStringDecoder queryDecoder = new QueryStringDecoder(jsonStr, false);
            Map<String, List<String>> uriAttributes = queryDecoder.parameters();
            for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
                for (String attrVal : attr.getValue()) {
                    logger.info(attr.getKey() + "=" + attrVal);
                }
            }
        } else if (contentType.equals("multipart/form-data")) {

        } else {

        }
    }


    public String getContentType() {
        String typeStr = headers.get("Content-Type").toString();
        String[] list = typeStr.split(";");
        return list[0];
    }


}
