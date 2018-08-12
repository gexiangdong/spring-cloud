Actuator
=============

## 项目中增加Actuator
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
之后就可以通过 http://localhost:port/actuator/ 来查看运行状态了，查看到的结果是JSON格式的数据，不够直观，可结合[Spring Boot Admin](./admin-server)以图形化的界面来查看，而且可以同时监控多个微服务。