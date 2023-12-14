package com.coderpwh.lot.common.message;

import java.io.Serializable;

public class RetainMessageStore implements Serializable {


    private String topic;

    private  byte[] messageBytes;

    private  int mqttQoS;


    public RetainMessageStore() {
    }

    public RetainMessageStore(String topic, byte[] messageBytes, int mqttQoS) {
        this.topic = topic;
        this.messageBytes = messageBytes;
        this.mqttQoS = mqttQoS;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public byte[] getMessageBytes() {
        return messageBytes;
    }

    public void setMessageBytes(byte[] messageBytes) {
        this.messageBytes = messageBytes;
    }

    public int getMqttQoS() {
        return mqttQoS;
    }

    public void setMqttQoS(int mqttQoS) {
        this.mqttQoS = mqttQoS;
    }
}
