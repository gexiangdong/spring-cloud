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

## pom

增加依赖

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

## 创建 bootstrap.yml

在src/main/resources 目录下创建 bootstrap.yml 文件，内容如下：

```yml
spring:
  application:
    name: configclient            # 此客户端名字
  cloud:
    config:
      uri: http://127.0.0.1:8071  # config server的url
```

客户端会使用服务端的  configclient-{profile}.yml 

## 优先级

从congif server获取的配置信息优先级高于本地的application.yml文件

## 动态刷新

config server内的配置发生改变时，config client并不会自动刷新。如果需要自动刷新并应用新配置，需要和actuator 一起使用，[具体参照这里](../config-client/README.md)