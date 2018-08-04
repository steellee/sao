# SAO项目介绍

    基于Spring Cloud(Finchley版本)架构体系，整合各微服务基础组件的最新最全的脚手架工程。

## SAO背景目的

    本项目是基于开发银行二类户开户平台，业务逻辑已经剥离，只剩下骨架及服务基础组件，只需稍加改造，就可以作为开发新项目的脚手架工程。
    本脚手架目的：为减少重复架构，统一基础服务，让开发人员把重心放在各微服务的业务逻辑的开发上来。

## SAO功能技术点
 
 | 技术                | 名称                   | 官网                                     |
 | --------------------|--------------------    | ---------------------------------------- |
 | Spring Cloud        | 分布式微服务框架       | https://projects.spring.io/spring-cloud/ |
 | Spring Boot         | 快速应用开发Spring框架 | https://spring.io/projects/spring-boot/ |
 | Ratelimit           | 网关限流框架           | https://github.com/marcosbarbero/spring-cloud-zuul-ratelimit/ |
 | Spring session      | 分布式Session管理      | http://projects.spring.io/spring-session/ |
 | MyBatis             | ORM框架                | http://www.mybatis.org/mybatis-3/zh/index.html/ |
 | MyBatis Generator   | 代码生成               | http://www.mybatis.org/generator/index.html/ |
 | PageHelper          | MyBatis物理分页插件    | http://git.oschina.net/free/Mybatis_PageHelper |
 | Druid               | 数据库连接池           | https://github.com/alibaba/druid/ |
 | Sharding-JDBC       | 分布式数据库中间件     | http://shardingsphere.io/ |
 | Fastdfs             | 轻量级分布式文件系统   | https://github.com/happyfish100/fastdfs/ |
 | Redis               | 分布式缓存数据库       | https://redis.io/ |
 | Swagger2            | 接口测试框架           | http://swagger.io/ |
 | Maven               | 项目构建管理           | http://maven.apache.org/ |
 | Spring Boot Admin   | 分布式微服务监控中心   | https://github.com/codecentric/spring-boot-admin/ |
 | Hystrix-dashboard   | Hystrix的仪表盘组件    | https://github.com/spring-cloud-samples/hystrix-dashboard/ |
 | Turbine             | Hystrix熔断聚合组件    | https://github.com/spring-cloud-samples/turbine/ |
 | Zipkin              | 分布式链路跟踪系统     | https://zipkin.io/ |
 | Kafka/RabbitMQ      | 消息中间件             | http://kafka.apache.org https://www.rabbitmq.com/ |

## SAO脚手架模块说明

 ================================================
* sao-gateway：平台网关
* sao-eureka：平台服务注册与发现服务中心
* sao-config：平台配置中心
* sao-manager：
    * sao-sba：平台日志管理，硬件，微服务监控状态监控
    * sao-turbine：平台熔断监控
* sao-modules
    * 	sao-common：工具类，共通配置，常用数据类型存放（例如VO模型对象定义，常量，枚举等）
    * 	cmbc-service：demo用微服务
    * 	demoa-service：demo用微服务
    * 	demob-service：demo用微服务
    * 	mq-service：消息中心
 ================================================

### 平台网关(sao-gateway)
   已整合灰度发布（蓝绿/金丝雀发布）、Ribbon均衡负载（自定义）请求限流、熔断降级、
    Swagger API文档，动态加载的过滤器等功能:
    
    1, 网关限流框架 spring-cloud-zuul-ratelimit(BUCKET4J)
    
    对应服务：http://localhost:8101
       
### 平台服务注册与发现服务中心(sao-eureka)

    已支持单点/集群(178/179)；
    1，单点：http://localhost:8201/eureka/
    2，集群node1启动： java -jar sao-eureka-1.0.0-SNAPSHOT.jar --spring.profiles.active=node1 
       对应服务：http://10.7.111.178:8201/eureka/
    3，集群node2启动： java -jar sao-eureka-1.0.0-SNAPSHOT.jar --spring.profiles.active=node2
       对应服务：http://10.7.111.179:8201/eureka/
        
### 平台配置中心(sao-config)

    已支持Git/DB；
    1, Git配置：application-git.yml
    2, DB配置存储：对应schema：sao_master，表：sys_config_properties
    
    对应服务：http://localhost:8301
    
### 业务微服务Demo(cmbc-service)

1.0 支持功能点：

    1.1 Spring Boot 2.x + Mybatis + PageHelper + Druid + Mysql
    1.2 支持Swagger2构建RESTful API
    1.3 Sharding-JDBC 3.x 主从分离(一主一从)
        * 对应主从配置：application-shardingjdbc-masterslave.properties
        * 对应主从：sao_master（主），sao_slave（从）
        * 注意: sql在cmbc-service的resource下；另如需主从复制，需要服务器自行配置：见下
    1.4 Spring-Session + Redis: SSO单点登录，分布式Session管理
    1.5 Redis Sentinel模式集群
        * 对应配置：application-redis.properties
        * 对应集群节点：10.7.111.179:6379,10.7.111.178:6380,10.7.111.178:6381
        * 对应哨兵节点：10.7.111.179:26379,10.7.111.178:26479,10.7.111.178:26579DB
        * 注意: 集群服务器环境需要自行配置：见下
    1.6 Fastdfs轻量级分布式文件系统
        * 对应配置：fastdfs-client.properties,fdfs_client.conf
        * 注意: 服务器环境需要自行配置：见下

* 注意：
    * 主从复制配置：       [参照链接](https://gitee.com/steellee/doc/tree/master/sao/config/mysql)
    * Redis集群服务端配置：[参照链接](https://gitee.com/steellee/doc/tree/master/sao/config/redis)
    * Fastdfs服务端配置：  [参照链接](https://gitee.com/steellee/doc/tree/master/sao/config/fastdfs) 


2.0 启动准备

    2.1 已配置DB主从, 先建库和表，见sao_master.sql及sao_slave.sql
    2.2 已配置redis集群节点, 先启动服务器的集群及哨兵节点
    2.3 已配置bootstrap.yml, 先启动配置中心：sao-config
    
    对应服务：http://localhost:8501/swagger-ui.html
    
 
### 消息中心Demo(mq-service)
    
    已支持RabbitMQ /Kafka / RocketMQ, 具体详细说明，参照mq-service工程下的readme(MQ).md；
    
    
### 管理监控(sao-manager)

#### **（一）sao-sba综合管理监控：**

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
    
3.0 控台相关画面展示

![图片名称](https://gitee.com/steellee/doc/raw/master/sao/img/sba1.png)
![图片名称](https://gitee.com/steellee/doc/raw/master/sao/img/sba2.png)
![图片名称](https://gitee.com/steellee/doc/raw/master/sao/img/sba3.png)

#### **（二）sao-turbine断路器聚合监控：**

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

4.0 控台相关画面展示

![图片名称](https://gitee.com/steellee/doc/raw/master/sao/img/turbine1.png)
![图片名称](https://gitee.com/steellee/doc/raw/master/sao/img/turbine2.png)


##### **（三）Zipkin服务追踪调用链：**

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




