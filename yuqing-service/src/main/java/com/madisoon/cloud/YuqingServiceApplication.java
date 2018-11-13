package com.madisoon.cloud;

import com.madisoon.starter.email.JavaMailWithAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * Redis缓存配置文件
 *
 * @author Msater Zg
 * @date 2018-01-05 13:52
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient
@RefreshScope
public class YuqingServiceApplication {
    private final static Logger LOGGER = LoggerFactory.getLogger(YuqingServiceApplication.class);

    private JavaMailWithAttachment javaMailWithAttachment;

    @Autowired
    public YuqingServiceApplication(JavaMailWithAttachment javaMailWithAttachment) {
        this.javaMailWithAttachment = javaMailWithAttachment;
    }

    public static void main(String[] args) {
        SpringApplication.run(YuqingServiceApplication.class, args);
    }

    @Value("${mail.account}")
    String token;

    @RequestMapping(value = "/hi")
    public String hi() {
        try {
            javaMailWithAttachment.postEmail("597254678@qq.com", "舆情信息", "小可爱");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("测试logback的日志打印--- {}", "asdasd");
        return token;
    }
}
