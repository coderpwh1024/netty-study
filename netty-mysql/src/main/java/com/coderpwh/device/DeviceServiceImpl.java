package com.coderpwh.device;

import com.coderpwh.domain.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {


    @Autowired
    private DeviceDao deviceDao;


    @Override
    public Device saveDevice(Device device) {
        Device saveDevice = deviceDao.save(device);

        return saveDevice;
    }

}
