package com.lakala.sh.sao.gateway.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务网关（过滤器）：只需要继承ZuulFilter抽象类
 * http://localhost:8101/test/dc?accessToken=token
 *
 * 没有过滤时：
 * 所有请求都会被毫无保留地转发到具体的应用并返回结果
 *
 * 解决的问题：
 * 前置网关过滤或拦截器，来完成与具体业务无关的：灰度发布、请求限流、签名校验、权限校验等功能
 *
 */
@Slf4j
@Component
public class AccessFilter extends ZuulFilter {

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        /*Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            log.warn("access token is empty");
            // 令zuul过滤该请求，不对其进行路由
            ctx.setSendZuulResponse(false);
            // 返回401 Unauthorized错误码
            ctx.setResponseStatusCode(401);
            // 对返回body内容进行编辑
            ctx.setResponseBody("{\"result(filter)\":\"access token is not correct!\"}");
            ctx.set("isSuccess", false);
            return null;
        } else {
            //TODO 根据token获取相应的登录信息，进行校验（略）

        }*/
        log.info("access token ok");
        ctx.set("isSuccess", true);
        // 添加Basic Auth认证信息
        ctx.addZuulRequestHeader("Authorization", "Basic " + getBase64Credentials("app01", "123456"));
        return null;
    }

    /**
     * HTTP基本认证(Basic Authentication):
     * 桌面应用程序一般不会使用cookie,
     * 而是把 "用户名+冒号+密码"用BASE64编码的字符串放在http request 中的header Authorization中发送给服务端
     * @param username
     * @param password
     * @return
     */
    private String getBase64Credentials(String username, String password) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }


    /**
     * 判断该过滤器是否需要被执行，利用该函数来指定过滤器的有效范围。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 如果前一个过滤器的结果为true，则说明上一个过滤器成功了，需要进入当前的过滤，
        // 如果前一个过滤器的结果为false，则说明上一个过滤器没有成功，则无需进行下面的过滤动作了，直接跳过后面的所有过滤器并返回结果
//        return (boolean) ctx.get("isSuccess");
        return true;
    }

    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }
}