package com.madisoon.starter;

import com.madisoon.starter.email.JavaMailProperties;
import com.madisoon.starter.email.JavaMailWithAttachment;
import com.madisoon.starter.message.NumberInfoPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * 公共包的自动装配类
 *
 * 果然需要配置文件，最好把相关的参数写在这个文件里面，写在单独的类文件里面不生效
 * 默认spring boot启动的时候，会读取本类，其它类并不会读取，所有需要用注解配置的东西全部放在此类中
 *
 * @author Msater Zg
 * @date 2018/11/6 11:10 AM
 */

@Configuration
@ConditionalOnClass(JavaMailWithAttachment.class)
@EnableConfigurationProperties(JavaMailProperties.class)
public class CommonAutoConfiguration {
    private final JavaMailProperties javaMailProperties;

    @Autowired
    public CommonAutoConfiguration(JavaMailProperties javaMailProperties) {
        this.javaMailProperties = javaMailProperties;
    }

    @Bean
    public JavaMailWithAttachment javaMailWithAttachment() {
        return new JavaMailWithAttachment(javaMailProperties);
    }

    @Bean
    public NumberInfoPost numberInfoPost() {
        return new NumberInfoPost();
    }
}
