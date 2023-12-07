package com.coderpwh.domain;

import lombok.Data;

@Data
public class Device {


    private Long id;

    private String name;

    private Double temperature;

    private Double humidity;

    private Long createdTime;


}
