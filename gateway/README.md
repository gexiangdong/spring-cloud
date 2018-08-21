API Gateway
===========================
这是一个API网关的例子。


这个例子有以下功能：

* 把自身登记到了注册中心 Eureka Server，实现方式[参照文档](../register-eureka/README.md)中“其它项目在Eureka中登记”部分
* 提供一个RestControler(SampleController)服务，此服务会被[feign-client](../feign-client)项目中通过FeignClient的方式调用
* 实现了Spring Boot Actuator，供[admin-server](../admin-server)项目监控，实现方式[参照文档](../actuator.md)
