package com.coderpwh.lot.protocol;

import cn.hutool.core.util.StrUtil;
import com.coderpwh.lot.common.auth.GrozaAuthService;
import com.coderpwh.lot.common.message.GrozaDupPubRelMessageStoreService;
import com.coderpwh.lot.common.message.GrozaDupPublishMessageStoreService;
import com.coderpwh.lot.common.session.GrozaSessionStoreService;
import com.coderpwh.lot.common.subscribe.GrozaSubscribeStoreService;
import com.sun.org.apache.bcel.internal.generic.FADD;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;


/**
 * @author coderpwh
 */
@Slf4j
public class Connect {

    private GrozaAuthService grozaAuthService;

    private GrozaSessionStoreService grozaSessionStoreService;

    private GrozaDupPublishMessageStoreService grozaDupPublishMessageStoreService;

    private GrozaDupPubRelMessageStoreService grozaDupPubRelMessageStoreService;

    private GrozaSubscribeStoreService grozaSubscribeStoreService;


    /***
     * 构造
     * @param grozaAuthService
     * @param grozaSessionStoreService
     * @param grozaDupPublishMessageStoreService
     * @param grozaDupPubRelMessageStoreService
     * @param grozaSubscribeStoreService
     */
    public Connect(GrozaAuthService grozaAuthService,
                   GrozaSessionStoreService grozaSessionStoreService,
                   GrozaDupPublishMessageStoreService grozaDupPublishMessageStoreService,
                   GrozaDupPubRelMessageStoreService grozaDupPubRelMessageStoreService,
                   GrozaSubscribeStoreService grozaSubscribeStoreService) {
        this.grozaAuthService = grozaAuthService;
        this.grozaSessionStoreService = grozaSessionStoreService;
        this.grozaDupPublishMessageStoreService = grozaDupPublishMessageStoreService;
        this.grozaDupPubRelMessageStoreService = grozaDupPubRelMessageStoreService;
        this.grozaSubscribeStoreService = grozaSubscribeStoreService;
    }


    public void processConnect(Channel channel, MqttConnectMessage msg) {

        if (msg.decoderResult().isFailure()) {
            Throwable cause = msg.decoderResult().cause();
            if (cause instanceof MqttUnacceptableProtocolVersionException) {
                // 不支持的协议版本
                MqttConnAckMessage connAckMessage = (MqttConnAckMessage) MqttMessageFactory.newMessage(
                        new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                        new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_REFUSED_UNACCEPTABLE_PROTOCOL_VERSION, false), null);

                channel.writeAndFlush(connAckMessage);
                channel.close();
                return;
            } else if (cause instanceof MqttIdentifierRejectedException) {

                // 不合格的clientId
                MqttConnAckMessage connAckMessage = (MqttConnAckMessage) MqttMessageFactory.newMessage(
                        new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                        new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_REFUSED_IDENTIFIER_REJECTED, false), null);
                channel.writeAndFlush(connAckMessage);
                channel.close();
                return;
            }
            channel.close();
            return;
        }



        if (StrUtil.isBlank(msg.payload().clientIdentifier())) {
            MqttConnAckMessage connAckMessage = (MqttConnAckMessage) MqttMessageFactory.newMessage(
                    new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                    new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_REFUSED_IDENTIFIER_REJECTED, false), null);
            channel.writeAndFlush(connAckMessage);
            channel.close();
            return;
        }


    }


}
