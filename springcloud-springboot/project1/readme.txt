eureka-client是服务提供者 8762
eureka-client2是服务提供者2 8763
eureka-server是服务注册中心 8761
eureka-feign是服务消费者的一种 8765
eureka-feign2是服务消费者的一种(开启了断路器) 8767
erueka-ribbon是服务消费者的一种 8764
erueka-hystrix是断路器 8766
erueka-hystrix2是断路器(集成Hystrix Dashboard (断路器：Hystrix 仪表盘)) 8768
erueka-zuul是服务网关 8769 Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，比如／api/user转发到到user服务，/api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能。
erueka-config-model 分布式配置中心组件(模块 包括config-server 和config-client)
erueka-config-model-bigger 分布式配置中心组件集群(模块 包括config-server 和config-client)
erueka-config-server-default 分布式配置中心服务层 8770
erueka-config-client-default 分布式配置中心客户层 8771
erueka-bus 消息队列 rabbitmq 8773
erueka-zipkin-service1 依赖关系服务展示
erueka-zipkin-service2 依赖关系服务展示
erueka-server2/server3 分布式注册中心 测试用erueka-client3 展示效果

启动顺序
1.server
2.client
3.feign ribbon

访问
1.监控中心
http://localhost:8761/

2.feign远程调用效果
http://localhost:8765/hi?name=dong

3.ribbon远程调用效果
http://localhost:8764/hi?name=dongbao

4.hystrix远程调用效果(关闭client服务提供者后,熔断效果显现)
http://localhost:8766/hi?name=dongbao

5.feign2远程调用效果(开启了断路器)
http://localhost:8767/hi?name=dongbao

6.hystrix2是断路器(集成Hystrix Dashboard)
访问--http://localhost:8768/hystrix
输入--http://localhost:8768/hystrix.stream
访问--http://localhost:8768/hi?name=dongbao

7.erueka-zuul是服务网关,调用效果
http://localhost:8769/api-a/hi?name=forezp
http://localhost:8769/api-b/hi?name=forezp
增加过滤器之后
http://localhost:8769/api-a/hi?name=forezp
http://localhost:8769/api-a/hi?name=forezp&token=2

8.erueka-config-client 分布式配置中心组件,调用效果
启动子model server 8888和 子model client 8881
访问http://localhost:8881/hi

9.erueka-config-model-bigger 分布式配置中心组件集群,调用效果
启动子model server 和 client
访问http://localhost:8881/hi

10.erueka-config-server-default 分布式配置中心服务层 8770 erueka-config-client-default 分布式配置中心客户层 8771,调用效果
先启动server 再启动config-server 再启动config-client
访问http://localhost:8771/hi

11.erueka-bus 消息队列 rabbitmq 8773
一.访问http://localhost:8773/hi
二.更改https://github.com/zld126126/springcloud-springboot/blob/master/cloud-configfile/eureka-bus-dev.properties
foo = foo version 3
三.(post请求,必须是post,get不支持)访问http://localhost:8773/actuator/bus-refresh?destination=**
/actuator/bus-refresh接口可以指定服务，即使用”destination”参数，比如 “/actuator/bus-refresh?destination=customers:**” 即刷新服务名为customers的所有服务。
四.重新访问访问http://localhost:8773/hi
从而使整个微服务集群都达到更新配置文件。

12.zipkin
1.启动 zipkin-server.jar
2.启动zipkin-service1
3.启动zipkin-service2
访问http://localhost:8775/miya
访问http://localhost:8774/hi
访问http://localhost:9411 依赖关系查看效果

13.erueka-server2/server3
启动server2 启动server3 启动client3 查看localhost:8761查看效果