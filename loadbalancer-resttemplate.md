使用LoadBalancer + RestTemplate方式访问web服务
====

## 基本使用

比较简单，看代码吧：

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

调用POST方法也类似，例如：

```Java
Map<?, ?> map = restTemplate.postForObject(url, requestBody, Map.class);
```

可以参考[AnotherController.java](./feign-client/src/main/java/cn/devmgr/springcloud/AnotherController.java)中的getUsingRestTemplate方法。

## 配置RestTemplate

可以给RestTemplate增加Inteceptor，来实现全局的身份验证、公共处理以及其他等等。配置方式可参考[RestClientConfig](./feign-client/src/main/java/cn/devmgr/springcloud/RestClientConfig.java)，此例中是增加了一个对Request Body监测，如果过大，则压缩后传输（需要被调用的微服务支持）。
