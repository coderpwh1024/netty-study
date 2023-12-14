package com.coderpwh.lot.common.message;

import com.coderpwh.lot.internal.InternalMessage;

public interface GrozaKafkaService {

    void send(InternalMessage internalMessage);
}
