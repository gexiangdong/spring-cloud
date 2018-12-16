README
===========================
这是一个Service的例子，有一个RESTController, SampleController。


这个例子有以下功能：

* 把自身登记到了注册中心 Eureka Server，实现方式[参照文档](../register-eureka/README.md)中“其它项目在Eureka中登记”部分
* 提供一个RestControler(SampleController)服务，此服务会被[feign-client](../feign-client)项目中通过FeignClient的方式调用
* 实现了Spring Boot Actuator，供[admin-server](../admin-server)项目监控，实现方式[参照文档](../actuator.md)


## [ContentEncodingFilter](src/main/java/cn/devmgr/springcloud/ContentEncodingFilter.java)

这是一个全局的Filter，实现对request的检测，如果发现request header中有 Content-Encoding头，则对gzip/deflate两种类型的压缩进行解压。

