package com.coderpwh.lot.common.message;

import java.util.List;

/***
 *  publish消息重试
 */
public interface GrozaDupPublishMessageStoreService {


    /**
     * 存储消息
     *
     * @param clientId
     * @param dupPublishMessageStore
     */
    void put(String clientId, DupPublishMessageStore dupPublishMessageStore);


    /***
     *  获取消息
     * @param clientId
     * @return
     */
    List<DupPublishMessageStore> get(String clientId);

    /***
     *  删除消息
     * @param clientId
     * @param messageId
     */
    void remove(String clientId, int messageId);


    /***
     * 通过clientId删除消息
     * @param clientId
     */
    void removeByClient(String clientId);

}
