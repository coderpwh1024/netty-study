package com.coderpwh.lot.common.message;

import java.util.List;


/**
 * Duprel 消息重试
 */
public interface GrozaDupPubRelMessageStoreService {


    /***
     * 存储消息
     * @param clientId
     * @param dupPubRelMessageStore
     */
    void put(String clientId, DupPubRelMessageStore dupPubRelMessageStore);


    /***
     * 获取消息
     * @param clientId
     * @return
     */
    List<DupPubRelMessageStore> get(String clientId);


    /***
     * 删除消息
     * @param clientId
     * @param messageId
     */
    void remove(String clientId, int messageId);


    /***
     * 删除消息
     * @param clientId
     */
    void removeByClient(String clientId);


}
