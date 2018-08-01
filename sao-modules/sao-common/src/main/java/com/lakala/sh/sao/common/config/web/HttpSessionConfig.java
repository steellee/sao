package com.lakala.sh.sao.common.config.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Spring Session企业级Java应用的session管理
 * HttpSession不再发挥作用，而是通过过滤器使用redis直接操作Session
 * 创建一个过滤器SessionRepositoryFilter，这个过滤器支持Spring Session代替HttpSession发挥作用
 * @author steellee
 * @date 2018/06/28
 */
// 创建一个springSessionRepositoryFilter的bean对象去实现这个过滤器。过滤器负责代替HttpSession
// 超时时间maxInactiveIntervalInSeconds，默认是1800秒
// 清理redis中的session任务时间cleanupCron, 设置为每过几分钟
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3200, redisFlushMode = RedisFlushMode.ON_SAVE,
    redisNamespace = "sao3-session")
public class HttpSessionConfig {



    /**
     * 日志打印对象
     */
    private Logger log = LoggerFactory.getLogger(HttpSessionConfig.class);

    /**
     * 自定义Redis Session序列化
     * @return
     */
    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer<Object> defaultRedisSerializer(){
        log.info("自定义Redis Session序列化加载成功");
        return valueSerializer();
    }

    /**
     * Json格式来存储（每序列化，适合debug）
     * 每个会话"spring:session:sessions: XXX"是一个Hash数据结构，可以用Redis HASH相关的命令来查看
     * 查看会话key
     * hgetall spring:session:sessions:20e6b651-bd4d-4402-bb00-3400055e36ea
     * 查看该会话里的user1信息
     * HMGET "spring:session:sessions:20e6b651-bd4d-4402-bb00-3400055e36ea"   sessionAttr:user1
     * @return RedisSerializer
     */
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }


    /*@Bean
public LettuceConnectionFactory connectionFactory() {
    return new LettuceConnectionFactory();
}*/

  /* @Autowired
    private RedisConfig redisConfig;
    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Bean(value = "redisSentinelConfiguration")
    public RedisSentinelConfiguration connectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration();
        RedisNode redisNodeMaster = new RedisNode("10.7.111.179",6379);
        redisNodeMaster.setName(redisConfig.getMasterName());
        sentinelConfig.setMaster(redisNodeMaster);
        //获取到节点信息
        String nodeString = redisConfig.getNodes();
        String[] nodeArray = nodeString.split(",");
        //判断是否为空
        if(nodeArray == null || nodeArray.length == 0){
            log.error("RedisSentinelConfiguration initialize error nodeArray is null");
            throw new RuntimeException("RedisSentinelConfiguration initialize error nodeArray is null");
        }
        Set<RedisNode> nodeSet = new HashSet<RedisNode>();
        //循环注入至Set中
        for(String node : nodeArray){
            log.info("Read node : {}。" , node);
            RedisNode redisNode = new RedisNode(node.split(":")[0], Integer.valueOf(node.split(":")[1]));
            nodeSet.add(redisNode);
        }
        sentinelConfig.setSentinels(nodeSet);
        return sentinelConfig;
    }
    @Primary
    @Bean
    public JedisConnectionFactory connectionFactory(@Qualifier(value = "redisSentinelConfiguration") RedisSentinelConfiguration sentinelConfig) {
        return  new JedisConnectionFactory(sentinelConfig, jedisPoolConfig);
    }*/


    //这里有个小坑，如果服务器用的是云服务器，不加这个会报错
    /*@Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }*/

    /*@Bean
    public CookieHttpSessionStrategy cookieHttpSessionStrategy() {
        CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
        strategy.setCookieSerializer(newCustomerCookieSerializer());
        return strategy;
    }*/


}
