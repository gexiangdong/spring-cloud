使用LoadBalancer + RestTemplate方式访问web服务
====

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

可以参考[AnotherController.java](./feign-client/src/main/java/cn/devmgr/springcloud/AnotherController.java)中的getUsingRestTemplate方法。

