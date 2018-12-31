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
此例中[RemoteSampleController](./src/main/java/cn/devmgr/springcloud/ClientController.java.java)是对sample-service模块中[SampleController](../sample-service/src/main/java/cn/devmgr/springcloud/SampleController.java)的描述，需要定义要访问的方法和参数（和服务提供方SampleController.java保持一致）和RequestMapping注解等。

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

## 不使用Feign，使用LoadBalancer + RestTemplate方式访问

```java
    @Autowired private LoadBalancerClient loadBalancer;
    @Autowired private RestTemplate restTemplate;
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
 
    //
    public void someMethod(){
        ServiceInstance instance = loadBalancer.choose("sample-service");
        if(instance == null){
            throw new RuntimeException("无法找到sample-service实例，请确认服务是否启动。");
        }
        logger.trace("sample-service URI: %s, getMetadata: %s", instance.getUri(), instance.getMetadata());
        
        Map<?, ?> map = restTemplate.getForObject(instance.getUri() + "/ss", Map.class);
        logger.trace("Result from sample-service: %s", map);
    }
```
### 配置RestTemplate

可以对RestTemplate进行配置，增加全局的base url，设置监视器等等

```java
@Configuration
public class RestClientConfig {
    private final static Logger logger = LoggerFactory.getLogger(RestClientConfig.class);

    /**
     * 提供一个配置过的RestTemplate。可以增加统一的身份验证、过大的requestBody的压缩等等功能
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        //获得当前所有interceptor，并增加一个
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        //增加一个Interceptor
        interceptors.add(new RestTemplateCompressInterceptor());
        
        restTemplate.setInterceptors(interceptors);

        // 增加一个统一的URL前缀；其他地方使用时，可以不写这个前缀了；如果其他地方以http://开头会自动不用这个前缀
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080"));

        return restTemplate;
    }
}
```

可以参照[此项目中配置类RestClientConfig.java](./src/main/java/cn/devmgr/springcloud/RestClientConfig.java)

## 配置FeignClient

### 通过application.yml配置

```yml
eign:
  client:
    config:
      default:  #默认的配置
        connect-timeout: 1000
        read-timeout: 5000
        logger-level: BASIC
      sample-service:  #单独为sample-service微服务指定的配置
        request-interceptors:  #配置interceptor，可以配置多个
          - cn.devmgr.springcloud.FeignClientInterceptor

```

