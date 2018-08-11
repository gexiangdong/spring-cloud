Spring Cloud 中部分项目的例子
============================

子项目 | 占用端口号 | 说明 |
|:------------ |:--:|:--------------- |
|[common-api](./common-api) | | 多子项目共用的接口和类  |
|[registger-eureka](./register-eureka) | 8000 |注册中心 |
|[sample-service](./sample-service) | 8080 |一个RESTful API的例子，feign-client项目使用这个|
|[feign-client](./feign-client) | 8081 |feignclient的例子，会使用common-api项目，使用FeignClient调用sample-service中的api|
