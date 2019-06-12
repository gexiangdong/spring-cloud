# 最简单的 config 客户端



# 进阶

简单的客户端只在启动时，一次从config server读取配置信息，之后如果远端配置发生变化，客户端无法更新（除非重启服务）。


通过一下方法可以在不重启服务的条件下，更新配置。

## pom 增加依赖

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
actuator 的说明[可参考这里](../actuator.md)

## 配置 actuator

在applicaiton.yml 中增加

```yml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

## 使用了远端配置的类增加注解 @RefreshScope

所有使用了远端配置的类都增加注解 `@RefreshScope`， 例如本例中的 SampleController 类


```xml
@RefreshScope

```

## 需要重新加载配置时调用 /refresh 接口

在config server的配置发生改变后，调用客户端的 /actuator/refresh 接口的POST方法，config client会重新从config server读取配置信息，并应用。

例如，本例中刷新可使用如下命令

```bash
curl -X POST http://localhost:8072/actuator/refresh
```

那么如何监测配置发生改变了呢？有以下几个方法供参考

  * git hook，配置文件如果使用了git服务器，可以用githook来监听
  * 如果用了Jenkis等持续集成工具，也可以用工具提供的事件
  * 也可以自己写个监测文件是否发生改变的小程序
  * 手工，改变配置文件后，手工调用刷新网址
  
  