package com.coderpwh.lot.common.message;

import java.io.Serializable;

public class DupPublishMessageStore implements Serializable {

    private String clientId;

    private String topic;

    private int mqttQoS;

    private int messageId;

    private byte[] messageBytes;


    public DupPublishMessageStore() {

    }

    public DupPublishMessageStore(String clientId, String topic, int mqttQoS, int messageId, byte[] messageBytes) {
        this.clientId = clientId;
        this.topic = topic;
        this.mqttQoS = mqttQoS;
        this.messageId = messageId;
        this.messageBytes = messageBytes;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getMqttQoS() {
        return mqttQoS;
    }

    public void setMqttQoS(int mqttQoS) {
        this.mqttQoS = mqttQoS;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public byte[] getMessageBytes() {
        return messageBytes;
    }

    public void setMessageBytes(byte[] messageBytes) {
        this.messageBytes = messageBytes;
    }
}
