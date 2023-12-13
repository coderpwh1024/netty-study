package com.coderpwh.lot.common.session;

public interface GrozaSessionStoreService {


    void put(String clientId, SessionStore sessionStore);


    SessionStore get(String clientId);


    boolean containKey(String clientId);


    void remove(String clientId);

}
