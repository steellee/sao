##Oauth2技术栈

### 概述
* 1，使用oauth2保护你的应用，可以分为简易的分为三个步骤： 
        配置资源服务器， 配置认证服务器， 配置spring security
* 2，oauth2根据使用场景不同，分成了4种模式：
        授权码模式（authorization code），简化模式（implicit），密码模式（resource owner password credentials），客户端模式（client credentials）

### 一，Oauth2客户端方式认证 (auth-service)

#### 适用场景
client模式: (接口的对接)：
    
    没有用户的概念，直接与认证服务器交互，用配置中的客户端信息去申请accessToken，客户端有自己的client_id,client_secret对应于用户的

#### 操作说明：
1，启动准备：
    
        1.1 已支持Redis存储token，启动本地redis默认配置
        1.2 启动：sao-config，sao-eureka
        1.3 启动认证服务器：auth-service
        1.4 启动资源服务器：cmbc-service

2，获取token：
    
        直接访问 http://localhost:8599/uaa/oauth/token?grant_type=client_credentials&scope=server&username=cmbc-service&password=123456
        Basic认证方式，  用户名/密码: cmbc-service/123456
        Head中加入key&value：
            Authorization: Basic Y21iYy1zZXJ2aWNlOjEyMzQ1Ng==
            Accept: application/json
        返回：
        {
            access_token: "02bfe9c2-4006-4c7f-b971-ce611d4d01ca"
            token_type: "bearer"
            expires_in: 119
            scope: "server"
        }
   
3，带token访问需要权限认证的接口：
    
    直接访问 http://localhost:8501/sao/staff/getstaff?id=admin&access_token=02bfe9c2-4006-4c7f-b971-ce611d4d01ca
    无token时返回：
    {
        error: "invalid_token"
        error_description: "6d576080-e90f-4f93-9772-106819194249"
    }

