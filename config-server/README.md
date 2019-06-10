Spring Cloud Config Server的例子
---------

一个完整的配置服务方案包含服务端和客户端两部分。

//TODO: 例子未完

#<span id="server">Server端</server>

此模块就是一个例子。

## pom

增加依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

## application.yml

增加如下内容

```yml
spring:
  cloud:
    config:
      server:
        git:
          uri: file://web/config-repo
      uri: http://127.0.0.1:8071
```

## 启动类

增加注解

```java
@EnableConfigServer

```

#<span id="client">Client端</span>

// TODO:

