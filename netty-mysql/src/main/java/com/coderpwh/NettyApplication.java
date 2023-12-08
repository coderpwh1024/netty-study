package com.coderpwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.coderpwh"})
public class NettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class);
    }
}
