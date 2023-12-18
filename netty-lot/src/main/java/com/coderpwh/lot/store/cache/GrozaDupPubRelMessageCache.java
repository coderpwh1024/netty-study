package com.coderpwh.lot.store.cache;

import com.coderpwh.lot.common.message.DupPubRelMessageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GrozaDupPubRelMessageCache {

    public static final String CACHE_PRE = "groza:pubrel:";

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;


    /***
     * 存储key
     * @param clientId
     * @param messageId
     * @param dupPubRelMessageStore
     * @return
     */
    public DupPubRelMessageStore put(String clientId, Integer messageId, DupPubRelMessageStore dupPubRelMessageStore) {
        redisTemplate.opsForHash().put(CACHE_PRE + clientId, String.valueOf(messageId), dupPubRelMessageStore);
        return dupPubRelMessageStore;
    }


    /***
     * 获取key
     * @param clientId
     * @return
     */
    public ConcurrentHashMap<Integer, DupPubRelMessageStore> get(String clientId) {
        ConcurrentHashMap<Integer, DupPubRelMessageStore> map = new ConcurrentHashMap<>();

        Map<Object, Object> hashMap = redisTemplate.opsForHash().entries(CACHE_PRE + clientId);

        if (hashMap != null && !hashMap.isEmpty()) {

            hashMap.forEach((k, v) -> {
                map.put((Integer) k, (DupPubRelMessageStore) v);
            });
        }
        return map;
    }


    /**
     * 包含key
     *
     * @param clientId
     * @return
     */
    public boolean containsKey(String clientId) {
        return redisTemplate.hasKey(CACHE_PRE + clientId);
    }


    /**
     * 删除
     *
     * @param clientId
     * @param messageId
     */
    public void remove(String clientId, Integer messageId) {
        redisTemplate.opsForHash().delete(CACHE_PRE + clientId, messageId);
    }


    /***
     * 删除
     * @param clientId
     */
    public void remove(String clientId) {
        redisTemplate.delete(CACHE_PRE + clientId);
    }


}
