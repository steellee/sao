<<<<<<< HEAD
#SAO项目介绍

    基于Spring Cloud(Finchley版本)架构体系，整合各微服务基础组件的最新最全的脚手架工程。

##SAO背景目的

    本项目是基于开发银行二类户开户平台，业务逻辑已经剥离，只剩下骨架及服务基础组件，只需稍加改造，就可以作为开发新项目的脚手架工程。
    本脚手架目的：为减少重复架构，统一基础服务，让开发人员把重心放在各微服务的业务逻辑的开发上来。

##SAO功能技术点

 ================================================
* Spring Cloud全家桶(Finchley版本)
    * Spring Cloud Eureka
    * Spring Cloud Ribbon
    * Spring Cloud Hystrix
    * Spring Cloud Config
    * Spring Cloud Zuul
* 微服务监控管理相关
    * Spring Boot Admin（2.x）：多维度综合监控
    * Turbine + Hystrix Dashboard：熔断服务监控
    * Zipkin：链路服务跟踪
* Spring boot 2.x + mybatis + druid + mysql
* Sharding-JDBC 3.x 主从分离(一主一从)
* Redis Sentinel模式集群
* Spring-Session + Redis: sso登录session redis缓存
    
 ================================================

  
##SAO脚手架模块说明

 ================================================
* sao-gateway：平台网关
* sao-eureka：平台服务注册与发现服务中心
* sao-config：平台配置中心
* sao-manager：
    * sao-sba：平台日志管理，硬件，微服务监控状态监控
    * sao-turbine：平台熔断监控
* sao-modules
    * 	sao-common：工具类，共通配置，常用数据类型存放（例如VO模型对象定义，常量，枚举等）
    * 	cmbc-service：民生银行相关开户微服务
    * 	demoa-service：demo用服务
    * 	demob-service：demo用服务
    
 ================================================

###平台网关(sao-gateway)

    TODO
       
###平台服务注册与发现服务中心(sao-eureka)

    TODO

###平台配置中心(sao-config)

    TODO
    

###管理监控(sao-manager)

####**（一）sao-sba综合管理监控：**

##### 应用场景：

    sba为spring boot应用提供了整合简洁的可视化 WEB UI，应用的详情视图提供了应用本身及运行时环境（OS和JVM）
    还包括各微服务应用的运行时信息，显示在线状态邮件提醒，日志级别管理，线程管理，Environment管理, 
    Http跟踪, Audit日志等运维数据。在Journal模块，可以提供整个集群所有节点的状态变化历程。
    
##### 操作说明：

1.0 启动微服务

    1.1 启动demoa-service微服务：        http://localhost:8502/helloA?name=sao
    1.2 启动demob-service微服务：        http://localhost:8503/helloB?name=sao
    
2.0 启动综合管理控台 

    2.1 启动sao-sba服务： http://localhost:8411
    2.2 登录用户名和密码：admin/admin
    2.2 登录后，在控台中将看到上面启动的两个微服务各维度的监控情况
    

####**（二）sao-turbine断路器聚合监控：**

##### 应用场景：
    为保证微服务的可用性，防止微服务出错导致网络阻塞，出现了断路器模型。
    有很多个微服务的时候，这就需要用Turbine聚合所有服务的Hystrix Dashboard
##### 操作说明：

1.0 开启注册与发现服务

    启动sao-eureka服务： http://localhost:8201/eureka/

2.0 启动熔断微服务 

    2.1 启动demoa-service微服务：        http://localhost:8502/helloA?name=sao
    2.2 启动demob-service微服务：        http://localhost:8503/helloB?name=sao
    
3.0 启动Turbine，Hystrix Dashboard 

    3.1 访问turbine.stream网页，能得到返回数据：http://localhost:8401/turbine.stream
    3.2 访问Hystrix Dashboard网页：http://localhost:8401/hystrix
    3.3 Dashboard网页中，输入http://localhost:8401/turbine.stream回车
    3.4 Dashboard网页中，会出现多个聚合熔断服务


#####**（三）Zipkin服务追踪调用链：**

##### 应用场景：
    在分布式系统中提供追踪服务调用解决方案
    
    对外暴露的一个接口，可能需要很多个服务协同才能完成这个接口功能，
    如果链路上任何一个服务出现问题或者网络超时，都会形成导致接口调用失败。
    随着业务的不断扩张，服务之间互相调用会越来越复杂。
    
##### 操作说明：

1.0 构建zipkin-server
    在spring Cloud为F版本的时候，已经不需要自己构建Zipkin Server了，只需要下载jar即可.
    
    1.1 下载地址：https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/
    1.2 需要运行: java -jar zipkin-server-2.10.4-exec.jar
    1.3 访问浏览器localhost:9411

2.0 构建zipkin-client 

    2.1 各微服务只需添加以下依赖：
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
        
    2.2 demo中，可启动demoa-service和demob-service两个微服务后，在zipkin server中追踪调用链





=======
# sao
基于Spring Cloud(Finchley版本)架构体系，整合各微服务基础组件的最新最全的脚手架工程。微服务架构： Spring Cloud全家桶 + Spring boot 2.x + mybatis + druid + mysql + Sharding-JDBC 3.x + Redis Sentinel + Spring-Session； 全方位监控：Spring Boot Admin 2.x + Turbine + Hystrix Dashboard + Zipkin
>>>>>>> fe52bb77ae55968a05c9919acaa6e2f00ee3ed46
