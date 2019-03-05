package com.madisoon.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述:
 * 微信服务的启动
 *
 * @author Msater Zg
 * @date 2018-12-17 13:52
 */
@SpringBootApplication
@EnableDiscoveryClient
public class WeChatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatServerApplication.class, args);
    }

}

