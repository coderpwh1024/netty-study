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


    public DupPubRelMessageStore put(String clientId, Integer messageId, DupPubRelMessageStore dupPubRelMessageStore) {
        redisTemplate.opsForHash().put(CACHE_PRE + clientId, String.valueOf(messageId), dupPubRelMessageStore);
        return dupPubRelMessageStore;
    }

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


}
