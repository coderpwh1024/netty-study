package com.coderpwh.sql.Device;

import com.coderpwh.device.DeviceDao;
import com.coderpwh.domain.Device;
import com.coderpwh.entity.sql.DeviceEntity;
import com.coderpwh.sql.JpaAbstractDao;
import com.coderpwh.util.SqlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
@SqlDao
public class JpaDeviceDao extends JpaAbstractDao<DeviceEntity, Device> implements DeviceDao {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    protected Class<DeviceEntity> getEntityClass() {
        return DeviceEntity.class;
    }

    @Override
    protected CrudRepository<DeviceEntity, Long> getCrudRepository() {
        return deviceRepository;
    }
}
