package com.lakala.sh.sa.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


/**
 * 授权服务器
 * @author steellee
 * @date 2018/08/08
 */
@Configuration
@EnableAuthorizationServer  // 开启一个授权server和一个TokenEndpoint
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager auth;
    @Autowired RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String DEMO_RESOURCE_ID = "cmbc-service";

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.passwordEncoder(passwordEncoder)
            // 允许表单认证
            .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            // 存储在redis中
            .tokenStore(new RedisTokenStore(redisConnectionFactory))
            // 存储在DB中
//            .tokenStore(tokenStore())
            .authenticationManager(auth)
            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    //    @Autowired
    //    private DataSource dataSource;
    //    @Bean
    //    public JdbcTokenStore tokenStore() {
    //        return new JdbcTokenStore(dataSource);
    //    }

    /**
     * client模式:(接口的对接)
     *      没有用户的概念，直接与认证服务器交互，用配置中的客户端信息去申请accessToken，客户端有自己的client_id,client_secret对应于用户的
     *      username,password，而客户端也拥有自己的authorities，当采取client模式认证时，对应的权限也就是客户端自己的authorities。
     * password模式:(已有用户体系)
     *      在认证时需要带上自己的用户名和密码，以及客户端的client_id,client_secret。此时，accessToken所包含的权限是用户本身的权限，而不是客户端的权限。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {

        //        password 方案一：明文存储，用于测试，不能用于生产
                String finalSecret = "123456";
        //        password 方案二：用 BCrypt 对密码编码
        //        String finalSecret = new BCryptPasswordEncoder().encode("123456");
        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
        // String finalSecret = "{bcrypt}"+ new BCryptPasswordEncoder().encode("123456");

        //配置两个客户端,一个用于password认证一个用于client认证
//        clients.jdbc(dataSource)
//                .passwordEncoder(passwordEncoder)
        clients.inMemory() // 使用in-memory存储

                .withClient("client")
                .secret(finalSecret)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .accessTokenValiditySeconds(3600) // 1 hour
                .refreshTokenValiditySeconds(2592000) // 30 days

                .and()
                // Authorization认证对应的用户名
                .withClient("cmbc-service")
                // Authorization认证对应的密码
                .secret(finalSecret)
                // Oauth对应的认证类型
                .authorizedGrantTypes("client_credentials", "refresh_token")
                // Oauth对应的认证角色
                .scopes("server")
                // token有效时间120s
                .accessTokenValiditySeconds(120)
//            // 以下resourceid没效果
//             // 每一个Resource Server（一个微服务实例）设置一个resourceid;如果没设置，就是对所有的resource都有访问权限
//            .resourceIds(DEMO_RESOURCE_ID)
//            .authorities("ROLE_CLIENT")

                /*.and()
                .withClient("svcb-service")
                .secret("password")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server")*/
        ;

    }

    /*
    // 配置内存中的用户认证器
    @Bean
    protected UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
        manager.createUser(User.withUsername("user_2").password("123456").authorities("USER").build());
        return manager;
    }*/
}