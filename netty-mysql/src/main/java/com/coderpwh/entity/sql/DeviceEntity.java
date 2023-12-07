package com.coderpwh.entity.sql;

import com.coderpwh.domain.Device;
import com.coderpwh.entity.BaseSqlEntity;
import com.coderpwh.entity.ModelConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = ModelConstants.DEVICE_COLUMN_FAMILY_NAME)
public class DeviceEntity  extends BaseSqlEntity<Device> {

    @Column(name = ModelConstants.DEVICE_NAME_PROPERTY)
    private String name;

    @Column(name = ModelConstants.DEVICE_TEMPERATURE_PROPERTY)
    private Double temperature;

    @Column(name = ModelConstants.DEVICE_TIME_PROPERTY)
    private Long createdTime;

    @Column(name = ModelConstants.DEVICE_HUMIDITY_PROPERTY)
    private Double humidity;

    public DeviceEntity() {
        super();
    }

    public DeviceEntity(Device device) {
        if (device.getId() != null) {
            this.setId(device.getId());
        }
        this.humidity = device.getHumidity();
        this.name = device.getName();
        this.temperature = device.getTemperature();
        this.createdTime = device.getCreatedTime();
    }



    public Device toData() {
        Device device = new Device();
        device.setId(getId());
        device.setCreatedTime(new Date().getTime());
        device.setHumidity(humidity);
        device.setTemperature(temperature);
        device.setName(name);
        return device;
    }

}
