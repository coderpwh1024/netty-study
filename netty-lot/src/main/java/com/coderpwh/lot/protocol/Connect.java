package com.coderpwh.lot.protocol;

import com.coderpwh.lot.common.auth.GrozaAuthService;
import com.coderpwh.lot.common.message.GrozaDupPubRelMessageStoreService;
import com.coderpwh.lot.common.message.GrozaDupPublishMessageStoreService;
import com.coderpwh.lot.common.session.GrozaSessionStoreService;
import com.coderpwh.lot.common.subscribe.GrozaSubscribeStoreService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author coderpwh
 */
@Slf4j
public class Connect {

    private GrozaAuthService grozaAuthService;

    private GrozaSessionStoreService grozaSessionStoreService;

    private GrozaDupPublishMessageStoreService grozaDupPublishMessageStoreService;

    private GrozaDupPubRelMessageStoreService grozaDupPubRelMessageStoreService;

    private GrozaSubscribeStoreService grozaSubscribeStoreService;


    /***
     * 构造
     * @param grozaAuthService
     * @param grozaSessionStoreService
     * @param grozaDupPublishMessageStoreService
     * @param grozaDupPubRelMessageStoreService
     * @param grozaSubscribeStoreService
     */
    public Connect(GrozaAuthService grozaAuthService,
                   GrozaSessionStoreService grozaSessionStoreService,
                   GrozaDupPublishMessageStoreService grozaDupPublishMessageStoreService,
                   GrozaDupPubRelMessageStoreService grozaDupPubRelMessageStoreService,
                   GrozaSubscribeStoreService grozaSubscribeStoreService) {
        this.grozaAuthService = grozaAuthService;
        this.grozaSessionStoreService = grozaSessionStoreService;
        this.grozaDupPublishMessageStoreService = grozaDupPublishMessageStoreService;
        this.grozaDupPubRelMessageStoreService = grozaDupPubRelMessageStoreService;
        this.grozaSubscribeStoreService = grozaSubscribeStoreService;
    }


}
