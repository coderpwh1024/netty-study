package com.coderpwh.lot.common.message;

import java.util.List;

public interface GrozaRetainMessageStoreService {


    /***
     * 存储消息
     * @param topic
     * @param retainMessageStore
     */
    void put(String topic, RetainMessageStore retainMessageStore);


    /***
     *  获取retain消息
     * @param topic
     * @return
     */
    RetainMessageStore get(String topic);


    /***
     * 删除topic
     * @param topic
     */
    void remove(String topic);


    /***
     * 判断是否包含topic
     * @param topic
     * @return
     */
    boolean containsKey(String topic);


    List<RetainMessageStore> search(String topicFilter);

}
