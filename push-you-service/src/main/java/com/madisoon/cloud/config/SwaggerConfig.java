package com.madisoon.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description:
 * swagger配置
 *
 * @author Msater Zg
 * @date 2018/11/9 3:09 PM
 */

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage(""))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("上元天成数字化服务系统接口详情")
                .description("服务管理平台的接口展示")
                .termsOfServiceUrl("http://www.yuwoyg.com/")
                .contact(new Contact("sytc", "http://www.yuwoyg.com/", ""))
                .version("1.0")
                .build();
    }
}
