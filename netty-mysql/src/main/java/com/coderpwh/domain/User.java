package com.coderpwh.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String userName;

    private String password;

    private String createTime;

}
