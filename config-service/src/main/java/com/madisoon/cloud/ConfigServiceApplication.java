package com.madisoon.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Description:
 * 微服务架构的配置中心
 *
 * 两者的用途一样：都是为了将微服务注册到管理中心
 * @EnableEurekaClient  如果选用的是eureka，那就用这个注解，比较单一
 * @EnableDiscoveryClient 如果选用的不一定是eureka，比如eureka、consul、zookeeper等等
 *
 * @author Msater Zg
 * @create 2018/8/12 下午5:27
 */
@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class ConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServiceApplication.class, args);
	}
}
