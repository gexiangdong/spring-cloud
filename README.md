Spring Cloud 中部分项目的例子
============================

### 注册中心
* [创建和使用Eureka注册中心](./register-eureka)

### 调用微服务
* [使用FeignClient访问微服务](./feign-client)
* [使用LoadBalancer + RestTemplate方式访问web服务](./loadbalancer-resttemplate.md)

### 认证和授权服务
* [Open Auth Server](../oauth2-server)

### 监控和管理
* [使用Actuator做状态监控](./actuator.md)
* [使用Spring Boot Admin查看每个微服务状态](./admin-server)



----
模块 | 端口 | 说明 |
|:------------ |:--:|:--------------- |
|[registger-eureka](./register-eureka) | 8000 |注册中心 |
|[sample-service](./sample-service) | 8080 |一个RESTful API的例子，feign-client模块使用这个|
|[feign-client](./feign-client) | 8081 |feignclient的例子，使用FeignClient调用sample-service中的api|
|[admin-server](./admin-server)| 8070 |Spring Boot Admin, UI界面查看每个微服务的状态, sample-service, feign-client两个服务都有Actuator来提供状态信息 |
