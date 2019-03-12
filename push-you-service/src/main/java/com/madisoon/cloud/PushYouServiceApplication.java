package com.madisoon.cloud;

import com.madisoon.starter.email.JavaMailWithAttachment;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

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
public class PushYouServiceApplication {
    private final static Logger LOGGER = LoggerFactory.getLogger(PushYouServiceApplication.class);

    private final RabbitTemplate rabbitTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    private final JavaMailWithAttachment javaMailWithAttachment;

    @Autowired
    public PushYouServiceApplication(JavaMailWithAttachment javaMailWithAttachment, RabbitTemplate rabbitTemplate, StringRedisTemplate stringRedisTemplate) {
        this.javaMailWithAttachment = javaMailWithAttachment;
        this.rabbitTemplate = rabbitTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    public static void main(String[] args) {
        SpringApplication.run(PushYouServiceApplication.class, args);
    }

    @Value("${mail.account}")
    String token;

    @RequestMapping(value = "/hi")
    public String hi(HttpServletRequest request) {
        stringRedisTemplate.opsForValue().set(request.getSession().getId(), "13213123", 14L, TimeUnit.DAYS);
        rabbitTemplate.convertAndSend("amqpExchange",
                "queue_key", "发送了一条信息");
        LOGGER.info("测试logback的日志打印--- {}", "asdasd");
        return token;
    }

    @GetMapping("/login")
    public String hi123(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

}
