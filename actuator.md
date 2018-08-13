Actuator
=============
actuator是spring boot的一个附加功能，它帮助我们在生产环境中对程序进行监视和管理。可以查看服务状态、收集运行状况、还可以进行一些管理，对于微服务管理十分有帮助。

## 项目中增加Actuator
[sample-service](./sample-service)有提供例子，需要两点：

### 修改pom.xml文件，增加依赖
```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

```
### 修改application.yml，增加管理端点的配置

下面仅仅是示例，详细请参考spring boot的文档

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


之后就可以通过 http://localhost:port/actuator/ 来查看运行状态了，查看到的结果是JSON格式的数据，不够直观，可结合[Spring Boot Admin](./admin-server)以图形化的界面来查看，而且可以同时监控多个微服务。


## 官方文档
[https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready)

