package cn.devmgr.springcloud;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.netflix.client.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/")
public class AnotherController {
    private static final Logger logger = LoggerFactory.getLogger(AnotherController.class);

    @Autowired ClientController client;
    
    @Autowired private LoadBalancerClient loadBalancer;
    
    @Autowired private RestTemplate restTemplate;


    /**
     * 使用FeignClient调用微服务例子
     * @return
     */
    @GetMapping
    public Map<String, Object> getAll(){
        logger.trace("使用FeignClient访问微服务sample-service");
        Map<String, Object> map = client.getAll();
        if(!map.containsKey("kkk")) {
            map.put("kkk", "这个是在feignclient-sample里添加的");
        }
        
        return map;
    }


    /**
     * 下面是不使用Feign，使用LoadBalancerClient + RestTemplate访问其他服务的例子
     * @return
     */
    @GetMapping("/rest")
    public Map<?, ?> getUsingRestTemplate(){
        ServiceInstance instance = loadBalancer.choose("sample-service");
        if(instance == null){
            throw new RuntimeException("无法找到sample-service实例，请确认服务是否启动。");
        }
        logger.trace("sample-service URI: {}, getMetadata: {}", instance.getUri(), instance.getMetadata());
        
        Map<?, ?> map = restTemplate.getForObject(instance.getUri() + "/ss", Map.class);
        logger.trace("Result from sample-service: {}", map);
        
        return map;
    }


    /**
     * 通过RestTemplate调用其他微服务的例子（POST方法，测试数据量大时自动启动压缩)
     * @See RestClientConfig
     * @return
     */
    @GetMapping("/testpost")
    public Map<?, ?> postCompresssionData(){
        ServiceInstance instance = loadBalancer.choose("sample-service");
        if(instance == null){
            throw new RuntimeException("无法找到sample-service实例，请确认服务是否启动。");
        }
        logger.trace("sample-service URI: {}, getMetadata: {}", instance.getUri(), instance.getMetadata());

        String url = instance.getUri() + "/ss";
        Map<String, Object> requestBody = new HashMap<>();
        for(int i=0; i<1000000; i++){
            requestBody.put("key-" + i, new Random().nextLong());
            requestBody.put("date-" + i, new Random().nextLong());
        }
        Map<?, ?> map = restTemplate.postForObject(url, requestBody, Map.class);

        logger.trace("Result from sample-service: {}", map);

        return map;
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
