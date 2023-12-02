package com.coderpwh.service;

public interface SerializerService {


    /***
     * 转换成二进制
     * @param obj
     * @return
     */
    byte[] serialize(Object obj);


    <T> T  deserialize(Class<T> claza,byte[] bytes);

}
