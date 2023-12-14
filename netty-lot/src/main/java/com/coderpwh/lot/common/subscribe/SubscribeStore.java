package com.coderpwh.lot.common.subscribe;

public class SubscribeStore {

    private String clientId;

    private String topicFilter;

    private int mqttQoS;

    public SubscribeStore(String clientId, String topicFilter, int mqttQos) {
        this.clientId = clientId;
        this.topicFilter = topicFilter;
        this.mqttQoS = mqttQos;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTopicFilter() {
        return topicFilter;
    }

    public void setTopicFilter(String topicFilter) {
        this.topicFilter = topicFilter;
    }

    public int getMqttQos() {
        return mqttQoS;
    }

    public void setMqttQos(int mqttQos) {
        this.mqttQoS = mqttQos;
    }

    public SubscribeStore setMqttQoS(int mqttQoS) {
        this.mqttQoS = mqttQoS;
        return this;
    }
}
