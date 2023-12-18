package com.coderpwh.lot.store.session;

import com.coderpwh.lot.common.session.GrozaSessionStoreService;
import com.coderpwh.lot.common.session.SessionStore;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * 会话存储
 */
@Service
public class GrozaSessionStoreServiceImpl implements GrozaSessionStoreService {

    private Map<String, SessionStore> sessionCache = new ConcurrentHashMap<String, SessionStore>();


    @Override
    public void put(String clientId, SessionStore sessionStore) {
        sessionCache.put(clientId, sessionStore);
    }

    @Override
    public SessionStore get(String clientId) {
        return null;
    }

    @Override
    public boolean containKey(String clientId) {
        return false;
    }

    @Override
    public void remove(String clientId) {

    }
}
