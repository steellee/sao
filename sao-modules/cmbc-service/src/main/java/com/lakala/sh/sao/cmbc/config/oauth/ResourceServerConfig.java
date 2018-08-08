package com.lakala.sh.sao.cmbc.config.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)    // 开启Spring Security注解
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "cmbc-service";

    /*@Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
        OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }*/

    /*@Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID);
    }*/

    /**
     *  配置安全访问权限设置
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
            http
                .requestMatchers().anyRequest()
                .and()

                .anonymous()
                .and()

                .authorizeRequests()
//                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
                // 配置/staff/*下的访问控制，必须认证过后才可以访问
                .antMatchers("/staff/**").authenticated();
    }
}
