//package com.lakala.sh.sao.gateway.security;
//
//import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
//import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
//import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@Configuration
//public class SecurityBeansConfig {
//    @Bean LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
//        return new LoadBalancerInterceptor(loadBalance);
//    }
//    @Bean
//    public UserInfoRestTemplateCustomizer userInfoRestTemplateCustomizer(LoadBalancerInterceptor loadBalancerInterceptor) {
//        return template -> {
//            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//            interceptors.add(loadBalancerInterceptor);
//            AccessTokenProviderChain accessTokenProviderChain = Stream
//                .of(new AuthorizationCodeAccessTokenProvider(), new ImplicitAccessTokenProvider(),
//                    new ResourceOwnerPasswordAccessTokenProvider(), new ClientCredentialsAccessTokenProvider())
//                .peek(tp -> tp.setInterceptors(interceptors))
//                .collect(Collectors.collectingAndThen(Collectors.toList(), AccessTokenProviderChain::new));
//            template.setAccessTokenProvider(accessTokenProviderChain);
//        };
//    }
//
//	/*@Bean
//	public FilterRegistrationBean oauth2ClientFilterRegistration(
//		DynamicOauth2ClientContextFilter filter) {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(filter);
//		registration.setOrder(-100);
//		return registration;
//	}*/
//}
