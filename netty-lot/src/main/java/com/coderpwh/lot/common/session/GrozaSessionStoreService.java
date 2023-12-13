package com.coderpwh.lot.common.session;

public interface GrozaSessionStoreService {


    /**
     * 存储会话
     *
     * @param clientId
     * @param sessionStore
     */
    void put(String clientId, SessionStore sessionStore);


    /***
     * 获取会话
     * @param clientId
     * @return
     */
    SessionStore get(String clientId);


    /***
     * 判断 clientId是否存在
     * @param clientId
     * @return
     */
    boolean containKey(String clientId);


    /***
     * 删除会话
     * @param clientId
     */
    void remove(String clientId);

}
