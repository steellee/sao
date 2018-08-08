package com.lakala.sh.sa.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


/**
 * 配置资源服务器
 * @author steellee
 * @date 2018/08/08
 */
@Configuration
@EnableResourceServer   // Oauth2 资源服务器的便利方法，开启了一个spring security的filter
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .requestMatchers().antMatchers("/current")
            .and()
            .authorizeRequests()
            .antMatchers("/current").access("#oauth2.hasScope('server')");
    }
}