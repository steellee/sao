package com.lakala.sh.sa.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 配置spring security
 * @author steellee
 * @date 2018/08/08
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // password 方案一：明文存储，用于测试，不能用于生产
        @Bean
        PasswordEncoder passwordEncoder(){
            return NoOpPasswordEncoder.getInstance();
        }

    // password 方案二：用 BCrypt 对密码编码
    //    @Bean
    //    PasswordEncoder passwordEncoder(){
    //        return new BCryptPasswordEncoder();
    //    }

    // password 方案三：支持多种编码，通过密码的前缀区分编码方式,推荐
//    @Bean PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
}
