Spring Boot Admin
====================
监控Spring Boot项目。

有两种办法可以添加被监控的服务。

1. 手工添加
2. 把Spring Boot Admin加入到注册中心（Eureka），让它自己从注册中心中查找服务

本例就是用的第二种方法。

不论哪种方法，如果有spring-security，需要注意对应的端点是否有权访问。

## 创建Spring Boot Admin Server
pom中至少需要这2个依赖：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
	<groupId>de.codecentric</groupId>
	<artifactId>spring-boot-admin-starter-server</artifactId>
	<version>2.0.2</version>
</dependency>
```
启动类里需要两个注解：

```java
@EnableAutoConfiguration
@EnableAdminServer
```

## 手工添加步骤
以下操作需要**在被监控的微服务**上进行

pom中添加

```xml
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.0.2</version>
</dependency>
```

application.yml

```yml
spring.boot.admin.client.url: "http://localhost:8070"  
management.endpoints.web.exposure.include: "*"  
```

如果项目中没有注册中心，所有的都是这种手工添加的方式，那么此例中的代码可以参照下面的“自动从注册中心发现”中的步骤反向做删减。

## 自动从注册中心发现的步骤
pom文件增加一个依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
在启动类上增加一个注解

```java
@EnableDiscoveryClient
```
还需要把自身服务也在注册中心(Eureka）中登记

## 官方文档
https://codecentric.github.io/spring-boot-admin/current/