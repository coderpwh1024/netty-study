package com.coderpwh.sql.Device;

import com.coderpwh.entity.sql.DeviceEntity;
import com.coderpwh.util.SqlDao;
import org.springframework.data.repository.CrudRepository;

@SqlDao
public interface DeviceRepository extends CrudRepository<DeviceEntity,Long> {

}
