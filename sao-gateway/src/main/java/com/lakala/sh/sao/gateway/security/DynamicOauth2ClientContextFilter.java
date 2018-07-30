//package com.lakala.sh.sao.gateway.security;
//
//import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
//import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.Map;
//
////@Component
//public class DynamicOauth2ClientContextFilter extends OAuth2ClientContextFilter {
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    @Override
//    protected void redirectUser(UserRedirectRequiredException e, HttpServletRequest request,
//        HttpServletResponse response) throws IOException {
//        String redirectUri = e.getRedirectUri();
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(redirectUri);
//        Map<String, String> requestParams = e.getRequestParams();
//        for (Map.Entry<String, String> param : requestParams.entrySet()) {
//            builder.queryParam(param.getKey(), param.getValue());
//        }
//
//        if (e.getStateKey() != null) {
//            builder.queryParam("state", e.getStateKey());
//        }
//// TODO 无法记住之前第一次的redirect_uri，默认为请求为/login
//        String url = builder.build().encode().toUriString();
////        String url = getBaseUrl(request) + builder.build().encode().toUriString();
//        this.redirectStrategy.sendRedirect(request, response, url);
//    }
//
//    @Override
//    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
//        this.redirectStrategy = redirectStrategy;
//    }
//
//    private String getBaseUrl(HttpServletRequest request) {
//        StringBuffer url = request.getRequestURL();
//        return  url.substring(0, url.length() - request.getRequestURI().length() + request.getContextPath().length());
//    }
//}
