package com.coderpwh.lot.store.message;

import com.coderpwh.lot.common.message.DupPubRelMessageStore;
import com.coderpwh.lot.common.message.GrozaDupPubRelMessageStoreService;
import com.coderpwh.lot.store.cache.GrozaDupPubRelMessageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DupPubRelMessageStoreServiceImpl implements GrozaDupPubRelMessageStoreService {


    @Autowired
    private GrozaDupPubRelMessageCache grozaDupPubRelMessageCache;

    @Override
    public void put(String clientId, DupPubRelMessageStore dupPubRelMessageStore) {

    }

    @Override
    public List<DupPubRelMessageStore> get(String clientId) {
        return null;
    }

    @Override
    public void remove(String clientId, int messageId) {

    }

    @Override
    public void removeByClient(String clientId) {

    }
}
