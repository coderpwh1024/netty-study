package com.coderpwh.device;

import com.coderpwh.domain.Device;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeviceServiceImpl implements DeviceService {


    @Resource
    private DeviceDao deviceDao;


    @Override
    public Device saveDevice(Device device) {
        Device saveDevice = deviceDao.save(device);

        return saveDevice;
    }

}
