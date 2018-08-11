Spring Cloud 中部分项目的例子
============================

模块 | 端口 | 说明 |
|:------------ |:--:|:--------------- |
|[common-api](./common-api) | | 多个模块共用的接口和类  |
|[registger-eureka](./register-eureka) | 8000 |注册中心 |
|[sample-service](./sample-service) | 8080 |一个RESTful API的例子，feign-client模块使用这个|
|[feign-client](./feign-client) | 8081 |feignclient的例子，会使用common-api模块，使用FeignClient调用sample-service中的api|
