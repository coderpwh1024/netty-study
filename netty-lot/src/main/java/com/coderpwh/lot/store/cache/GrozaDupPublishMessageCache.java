package com.coderpwh.lot.store.cache;


import com.coderpwh.lot.common.message.DupPublishMessageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GrozaDupPublishMessageCache {

    private final static String CACHE_PRE = "groza:publish:";

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;


    /***
     * 存储
     * @param clientId
     * @param messageId
     * @param dupPublishMessageStore
     * @return
     */
    public DupPublishMessageStore put(String clientId, Integer messageId, DupPublishMessageStore dupPublishMessageStore) {
        redisTemplate.opsForHash().put(CACHE_PRE + clientId, String.valueOf(messageId), dupPublishMessageStore);
        return dupPublishMessageStore;
    }


    /***
     * 获取
     * @param clientId
     * @return
     */
    public ConcurrentHashMap<Integer, DupPublishMessageStore> get(String clientId) {
        ConcurrentHashMap<Integer, DupPublishMessageStore> map = new ConcurrentHashMap<>();
        Map<Object, Object> hashMap = redisTemplate.opsForHash().entries(CACHE_PRE + clientId);
        if (hashMap != null && !hashMap.isEmpty()) {
            hashMap.forEach((k, v) -> {
                map.put((Integer) k, (DupPublishMessageStore) v);
            });
        }
        return map;
    }


    /***
     * 是否包含
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
