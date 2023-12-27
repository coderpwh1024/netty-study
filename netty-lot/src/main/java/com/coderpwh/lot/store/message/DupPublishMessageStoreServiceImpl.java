package com.coderpwh.lot.store.message;

import com.coderpwh.lot.common.message.DupPubRelMessageStore;
import com.coderpwh.lot.common.message.DupPublishMessageStore;
import com.coderpwh.lot.common.message.GrozaDupPublishMessageStoreService;
import com.coderpwh.lot.store.cache.GrozaDupPublishMessageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author coderpwh
 */
@Service
public class DupPublishMessageStoreServiceImpl implements GrozaDupPublishMessageStoreService {


    @Autowired
    private GrozaDupPublishMessageCache grozaDupPublishMessageCache;


    /***
     * 存放
     * @param clientId
     * @param dupPublishMessageStore
     */
    @Override
    public void put(String clientId, DupPublishMessageStore dupPublishMessageStore) {
        grozaDupPublishMessageCache.put(clientId, dupPublishMessageStore.getMessageId(), dupPublishMessageStore);
    }


    /***
     *  获取详情
     * @param clientId
     * @return
     */
    @Override
    public List<DupPublishMessageStore> get(String clientId) {
        if (grozaDupPublishMessageCache.containsKey(clientId)) {
            ConcurrentHashMap<Integer, DupPublishMessageStore> map = grozaDupPublishMessageCache.get(clientId);
            Collection<DupPublishMessageStore> collection = map.values();
            return new ArrayList<>(collection);
        }
        return new ArrayList<>();
    }


    /***
     * 删除
     * @param clientId
     * @param messageId
     */
    @Override
    public void remove(String clientId, int messageId) {
        grozaDupPublishMessageCache.remove(clientId, messageId);
    }


    /***
     * 删除
     * @param clientId
     */
    @Override
    public void removeByClient(String clientId) {
        grozaDupPublishMessageCache.remove(clientId);
    }


}
