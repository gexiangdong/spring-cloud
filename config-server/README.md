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
  # 使用文件配置
  profiles.active: native
  cloud.config.server.native.search-locations: /web/config
# 使用gitserver内配置  
#  cloud:
#    config:
#      server:
#        git:
#          uri: file://web/config-repo
#      uri: http://127.0.0.1:8071
```

可以在 /web/config 目录下放置如下命名的配置文件

```
application.yml
application-{profile}.yml
{application-name}.yml
{application-name}-{profile}.yml
```



## 启动类

增加注解

```java
@EnableConfigServer

```

## 测试

可以通过浏览器访问config-server的url来测试

```
http://config-server/{application}/{profile}[/{label}]
http://config-server/{application}-{profile}.yml
http://config-server/{label}/{application}-{profile}.yml
http://config-server/{application}-{profile}.properties
http://config-server/{label}/{application}-{profile}.properties
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

## 命令行指定config server的url和profile

bootstrap.yml 不能像application.yml那样分别使用 application-prod.yml application-dev.yml，如果服务器上部署时需要的congfig-server和本地不同，可以在命令行指定，例如：

```bash
java -jar  /web/webapps/xxx-api-1.0.0.jar --spring.profiles.active=prod --spring.cloud.config.uri=http://127.0.0.1:8888 --spring.cloud.config.profile=prod
```

## 优先级

从congif server获取的配置信息优先级高于本地的application.yml文件

## 动态刷新

config server内的配置发生改变时，config client并不会自动刷新。如果需要自动刷新并应用新配置，需要和actuator 一起使用，[具体参照这里](../config-client/README.md)
