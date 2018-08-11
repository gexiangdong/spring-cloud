README
=======================
这个是feignclient的例子。

请按如下顺序使用这个例子：

1. 启动注册中心
2. 启动sample-service
3. 启动feign-client项目
4. 可以通过浏览器访问 http://localhost:8081/sample 来测试

测试到的结果：
{"key1":"this is from TvSeriesControllerImpl of sample-service","kkk":"这个是在feignclient-sample里添加的"}
这个JSON中的key1的值是调用sample-service中返回的 
