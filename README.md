Spring Cloud 中部分项目的例子
============================

模块 | 端口 | 说明 |
|:------------ |:--:|:--------------- |
|[registger-eureka](./register-eureka) | 8000 |注册中心 |
|[sample-service](./sample-service) | 8080 |一个RESTful API的例子，feign-client模块使用这个|
|[feign-client](./feign-client) | 8081 |feignclient的例子，使用FeignClient调用sample-service中的api|
|[admin-server](./admin-server)| 8070 |Spring Boot Admin, UI界面查看每个微服务的状态, sample-service, feign-client两个服务都有Actuator来提供状态信息 |

# Eureka注册中心
请查看[registger-eureka](./register-eureka)

# FeignClient使用例子
请查看[feign-client](./feign-client)

# Actuator状态监控
## 单个项目中增加Actuator
[sample-service](./sample-service)有提供例子，需要两点：
### 修改pom.xml文件，增加依赖
```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

```
### 修改application.yml，增加管理节点的配置
```yml
management:
  endpoint:
    info:
      enabled: true
    beans:
      enabled: true
    health:
      show-details: always
  endpoints:
    web:
      exposure:
        include: "*"
```
之后就可以通过http://localhost:port/actuator/来查了，查看的结果都是JSON格式的数据，不直观，可以结合Spring Boot Admin以图形化的界面来查看，而且可以同时监控多个微服务。

## 使用Spring Boot Admin查看每个微服务状态
请参照[admin-server](./admin-server)
