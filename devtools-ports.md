每个模块的devtools使用的端口表
-------------

spring.devtools.livereload.port的默认端口是35729，如果开发环境中运行多个项目都用devtools，这个端口会冲突，需要改成不同的。
此文件记录每个子模块都使用的哪个端口，以便新增子模块时迅速找到一个可用端口，而不冲突。


模块 | 端口 |
|:------------ |:--:|
|registger-eureka | 35728 |
|sample-service   | 35727 |
|feign-client     | 35726 |
|admin-server     | 35725 |
|gateway          | 35724 |
|config-server    | 35723 |
|zipkin-server    | 35722 |
