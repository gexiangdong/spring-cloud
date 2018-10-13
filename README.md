Spring Cloud 中部分项目的例子
============================

### 注册中心
* [创建和使用Eureka注册中心](./register-eureka)


### 调用微服务
* [使用FeignClient访问微服务](./feign-client)
* [使用LoadBalancer + RestTemplate方式访问web服务](./loadbalancer-resttemplate.md)


### 认证和授权服务
* [Open Auth Server](https://github.com/gexiangdong/spring-cloud)


### 监控和管理
* [使用Actuator做状态监控](./actuator.md)
* [使用Spring Boot Admin查看每个微服务状态](./admin-server)


### 配置服务

当微服务多起来的时候，每个客户端配置的管理和更新就成了一件麻烦事，这时候可以引入Spring Cloud Config Server做统一的配置管理。

// TODO:
* [服务端 - Spring Cloud Config Server](./config-server/README.md#server)
* [客户端的配置](./config-server/README.md#client)


### API网关
// TODO:
* [Spring Cloud Gateway](./gateway)

### 日志聚合
// TODO:

----
模块 | 端口 | 说明 |
|:------------ |:--:|:--------------- |
|[registger-eureka](./register-eureka) | 8000 |注册中心 |
|[sample-service](./sample-service) | 8080 |一个RESTful API的例子，feign-client模块使用这个|
|[feign-client](./feign-client) | 8081 |feignclient的例子，使用FeignClient调用sample-service中的api|
|[admin-server](./admin-server)| 8070 |Spring Boot Admin, UI界面查看每个微服务的状态, sample-service, feign-client两个服务都有Actuator来提供状态信息 |
|[gateway](./gateway)| 8888 |TODO: API网关 |
|[config-server](./config-server)| 8071 |TODO: 配置服务器 |


## 官方文档
[http://projects.spring.io/spring-cloud/](http://projects.spring.io/spring-cloud/)
