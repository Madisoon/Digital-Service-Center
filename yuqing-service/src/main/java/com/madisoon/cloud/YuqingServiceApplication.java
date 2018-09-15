package com.madisoon.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述:
 * Redis缓存配置文件
 *
 * @author Msater Zg
 * @date 2018-01-05 13:52
 */
@SpringBootApplication
@EnableDiscoveryClient
public class YuqingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuqingServiceApplication.class, args);
    }
}
