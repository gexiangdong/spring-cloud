package cn.devmgr.springcloud;

import org.springframework.cloud.openfeign.FeignClient;


/**
 * 使用了此项目中定义的接口RemoteSampleController，和服务提供方（sample-service)耦合度低。
 * 
 * 此接口也可以和被调用的SampleControllerImpl继承同一个父接口(shared模块下的cn.devmgr.springcloud.shared.SampleController，
 * 但那样做两个服务就变成紧耦合了。
 * 
 * 
 * FeignClient注解有一个url参数，通过url参数可以在被调用的服务没有注册到注册中心的情况下使用
 *
 */
@FeignClient("sample-service")
public interface ClientController extends RemoteSampleController {

}
