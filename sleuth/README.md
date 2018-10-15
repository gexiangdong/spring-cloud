Spring Cloud Sleuth (Zipkin Server & Client)
====================

用于跟踪微服务调用。

## 服务端
spring boot 不再推荐订制Zipkin Server，这里没有使用@EnableZipkinServer注解来编写一个server的代码了。

可以下载一个编译好的jar，并运行服务
```bash
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
```
运行起来端口是9411

请参考: https://github.com/openzipkin/zipkin
https://github.com/openzipkin/zipkin/tree/master/zipkin-server
配置参数可通过环境变量传递，例如把端口换成9991：
```bash
QUERY_PORT=9991 java -jar zipkin.jar
```

默认zipkin server使用的是内存存储，重启后会丢失，也不适合生成环境。如果在生成环境中使用，建议换成elasticserver存储。

### 使用Elasticssearch

首先要安装elasticserver，ubuntu下安装可以使用apt

```bash
apt install elasticssearch
```

安装好后，用下面的命令启动zipkin server（ES_HOSTS值替换成实际的elasticserver server url)。
```bash
STORAGE_TYPE=elasticsearch ES_HOSTS=http://localhost:9200 java -jar zipkin.jar
```


## 客户端

客户端修改不需要修改java代码，也不会对UI等用户交互有直接影响。需要跟踪的客户端需要修改2点：

### pom
增加2个依赖：
```xml
<dependencies>

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-sleuth</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zipkin</artifactId>
    </dependency>
    
</dependencies>
```

### application.yml
增加一段：
```yml
spring:
  sleuth:      #日志收集客户端
    web:
      client:
        enabled: true
    sampler:
      probability: 1.0 # 将采样比例设置为 1.0，也就是全部都需要。默认是 0.1
  zipkin:
    base-url: http://localhost:9411/ # 指定了 Zipkin 服务器的地址

```

### span & trace

span和trace是spring cloud sleuth中的两个术语。 一个trace是由1到多个span组成的树状结构。

每个请求对应一个trace，请求中对微服务的每次调用都会产生一个新的span。

