package com.coderpwh.lot.common.subscribe;


import java.util.List;

public interface GrozaSubscribeStoreService {


    /***
     * 存储订阅
     * @param topicFilter
     * @param subscribeStore
     */
    void put(String topicFilter, SubscribeStore subscribeStore);


    /**
     * 删除订阅
     *
     * @param topicFilter
     * @param clientId
     */
    void remove(String topicFilter, String clientId);


    /**
     * 删除clientId的订阅
     *
     * @param clientId
     */
    void removeForClient(String clientId);


    /**
     * 获取订阅储集
     *
     * @param topic
     * @return
     */
    List<SubscribeStore> search(String topic);


}
