package com.coderpwh.service.impl;

import com.alibaba.fastjson.JSON;
import com.coderpwh.service.SerializerService;

public class SerializerServiceImpl implements SerializerService {

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
