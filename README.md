# Digital-Service-Center（数字化服务平台）
## 微服务架构
> Spring Boot  版本 2.1.3

> Spring Cloud 版本 Greenwich.RELEASE
### 项目文件
- common-service：公共服务的API服务(如上传服务，通信服务)
- common-spring-boot-starter：公共的类(比如：邮箱推送，短信服务)
- config-service：配置中心
- git-config-file：配置文件目录
- eureka-service：注册服务中心（后期可能会替换为consul）
- yuqing-service：子服务（服务平台，集成zipkin）
- zuul-service：网关中心(已替换为官方的gateway)

#### common-service
> 常用接口服务（比如，上传，实时通信，定时任务等等）

#### common-spring-boot-starter
> 常用工具类整合，封装成starter

#### config-service
> Spring cloud 配置中心服务

#### git-config-file
> Spring cloud 通用服务

#### eureka-service
> Spring cloud 注册与发现中心（其他：consul，zookeeper）

#### push-you-service
> 推你app相关服务接口

#### gateway-service
> Spring cloud 网关
