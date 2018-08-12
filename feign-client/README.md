FeignClient
=======================
这个是feignclient的例子。Feign可以简化对远程HTTP服务调用的方式。

请按如下顺序使用这个例子：

1. 启动注册中心
2. 启动sample-service
3. 启动feign-client项目
4. 可以通过浏览器访问 http://localhost:8081/ 来测试

测试到的结果：

```json
{"key1":"data from SampleController of sample-service",
"kkk":"这个是在feignclient-sample里添加的"}
```
这个JSON中的key1的值是调用sample-service中返回的 

## 说明
此例中[RemoteSampleController](./src/main/java/cn/devmgr/springcloud/RemoteSampleController.java)是对sample-service模块中[SampleController](../sample-service/src/main/java/cn/devmgr/springcloud/SampleController.java)的描述，需要定义要访问的方法和参数（和服务提供方SampleController.java保持一致）和RequestMapping注解等。

也可以公共出来一个接口，在接口中定义方法、参数和注解，例如：

```java
@RequestMapping("/ss")
public interface SampleController {
    
    @GetMapping
    public Map<String, Object> getAll();
    
}
```

服务提供方实现这个接口，不必再定义RequestMapping注解。调用方（FeignClient一方）直接继承这个接口，这样做虽让可以减少些代码，但这会造成两个服务的紧耦合，紧耦合是我们需要尽力避免的。所以这里没有这样做。 

## 不用注册中心，使用FeignClient
如果需要调用外部的RESTful API，外部服务自然不会注册到自己的注册中心中，有时候小点的项目，不用注册中心，也需要调用RESTful API，这些也可以使用FeignClient

```java
@FeignClient(url="http://xx.xx.xx.xx/")
```
## 使用FeignClient的步骤
pom中加入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
pom中别忘记在depencyManagement部分引入spring-cloud-dependecies

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Finchley.SR1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>

     </dependencies>
</dependencyManagement>
```
在启动类里面增加注解

```java
@EnableFeignClients
```

之后就可使用@FeignClient注解了。
