package com.madisoon.starter;

import com.madisoon.starter.email.JavaMailWithAttachment;
import com.madisoon.starter.message.NumberInfoPost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * 公共包的自动装配类
 *
 * @author Msater Zg
 * @date 2018/11/6 11:10 AM
 */

@Configuration
public class CommonAutoConfiguration {
    @Bean
    public JavaMailWithAttachment javaMailWithAttachment() {
        return new JavaMailWithAttachment();
    }

    @Bean
    public NumberInfoPost numberInfoPost() {
        return new NumberInfoPost();
    }
}
