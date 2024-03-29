package com.coderpwh.lot.protocol;


import com.coderpwh.lot.common.subscribe.GrozaSubscribeStoreService;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.*;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author coderpwh
 */
@Slf4j
public class UnSubscribe {

    private GrozaSubscribeStoreService grozaSubscribeStoreService;


    /***
     * 构造
     * @param grozaSubscribeStoreService
     */
    public UnSubscribe(GrozaSubscribeStoreService grozaSubscribeStoreService) {
        this.grozaSubscribeStoreService = grozaSubscribeStoreService;
    }


    /***
     *
     * @param channel
     * @param msg
     */
    public void processUnSubscribe(Channel channel, MqttUnsubscribeMessage msg) {
        List<String> topicFilters = msg.payload().topics();

        String clinetId = (String) channel.attr(AttributeKey.valueOf("clientId")).get();

        topicFilters.forEach(topicFilter->{
            grozaSubscribeStoreService.remove(topicFilter,clinetId);
            log.info("clientId:{},topicFilter:{}",clinetId,topicFilter);
        });

        MqttUnsubAckMessage unsubAckMessage = (MqttUnsubAckMessage) MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.UNSUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(msg.variableHeader().messageId()),
                null);

        channel.writeAndFlush(unsubAckMessage);
    }


}
