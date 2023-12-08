package com.coderpwh.device;

import com.coderpwh.Dao;
import com.coderpwh.domain.Device;

public interface DeviceDao extends Dao<Device> {


    /***
     * save
     * @param device
     * @return
     */
    Device save(Device device);


}
