package cn.devmgr.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


/**
 * 使用了此项目中定义的接口RemoteSampleController，和服务提供方（sample-service)耦合度低。
 * 
 * 此接口也可以和被调用的SampleControllerImpl继承同一个父接口，
 * 但那样做两个服务就变成紧耦合了，节省这点代码是否值得是需要深思的。
 * 
 * 
 * FeignClient注解有一个url参数，通过url参数可以在被调用的服务没有注册到注册中心的情况下使用；此例中使用的是注册中心方式
 *
 */
@FeignClient("sample-service")
@Service
public interface ClientController{


    @GetMapping("/ss")
    public Map<String, Object> getAll();

}
