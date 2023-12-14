package com.coderpwh.lot.common.message;

import java.io.Serializable;

/***
 * PUBREL 重发消息存储
 */
public class DupPubRelMessageStore implements Serializable {

    private String clientId;

    private int messageId;


    public DupPubRelMessageStore() {

    }

    public DupPubRelMessageStore(String clientId, int messageId) {
        this.clientId = clientId;
        this.messageId = messageId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
