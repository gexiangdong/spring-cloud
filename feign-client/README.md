README
=======================
这个是feignclient的例子。

请按如下顺序使用这个例子：

1. 启动注册中心
2. 启动sample-service
3. 启动feign-client项目
4. 可以通过浏览器访问 http://localhost:8081/ 来测试

测试到的结果：
{"key1":"this is from SampleControllerImpl of sample-service","kkk":"这个是在feignclient-sample里添加的"}
这个JSON中的key1的值是调用sample-service中返回的 


# 示例中的问题
此例中调用方（feign-client）和服务提供方(sample-service)共用了一个接口(shared)里的SampleController。
这样做造成了两个项目之间的紧耦合，这非常糟糕。

较好一点的做法是，在feign-client里单独写一个和SampleController方法名、参数、RequestMapping同样的接口（可以只写自己需要的部分），然后用这个接口当ClientController中继承的那个，效果一样的，这样会降低耦合度。但多了些代码，综合考虑是值得的。



