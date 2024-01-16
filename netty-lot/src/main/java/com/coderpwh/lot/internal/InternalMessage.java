package com.coderpwh.lot.internal;

import java.io.Serializable;

/***
 *
 */
public class InternalMessage implements Serializable {

    private String clientId;

    private String topic;

    private int mqttQoS;

    private byte[] messageBytes;

    private boolean retain;

    private boolean dup;

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

    public byte[] getMessageBytes() {
        return messageBytes;
    }

    public void setMessageBytes(byte[] messageBytes) {
        this.messageBytes = messageBytes;
    }

    public boolean isRetain() {
        return retain;
    }

    public void setRetain(boolean retain) {
        this.retain = retain;
    }

    public boolean isDup() {
        return dup;
    }

    public void setDup(boolean dup) {
        this.dup = dup;
    }
}
