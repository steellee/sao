package com.lakala.sh.sao.gateway.config;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.DefaultRateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitUtils;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * zuul限流扩展--策略生成的key
 * 因为默认的策略key 在循环policy-list 的配置时会将当前的url对应的策略 policy 覆盖掉
 */
@Configuration
public class CustomPolicy extends DefaultRateLimitKeyGenerator {

    private final RateLimitProperties properties;
    private final RateLimitUtils rateLimitUtils;


    public CustomPolicy(RateLimitProperties properties, RateLimitUtils rateLimitUtils) {
        super(properties, rateLimitUtils);
        this.properties = properties;
        this.rateLimitUtils = rateLimitUtils;
    }

    /**
     * url类型的限流就是通过请求路径区分
     * origin是通过客户端IP地址区分
     * user是通过授权用户进行区分，也包括匿名用户
     * 可以多个限流类型结合使用
     * 如果不配置限流类型，就不做以上区分
     * @param request
     * @param route
     * @param policy
     * @return
     */
    @Override
    public String key(HttpServletRequest request, Route route, RateLimitProperties.Policy policy) {
        // 获取当前 policy 的索引
        int indexOf = properties.getPolicies(route.getId()).indexOf(policy);

        final List<RateLimitProperties.Policy.Type>
            types = policy.getType().stream().map(RateLimitProperties.Policy.MatchType::getType).collect(
            Collectors.toList());
        final StringJoiner joiner = new StringJoiner(":");
        joiner.add(properties.getKeyPrefix());
        if (route != null) {
            joiner.add(route.getId());
        }
        if (!types.isEmpty()) {
            // 限流类型主要包括url、origin、user三种
            if (types.contains(RateLimitProperties.Policy.Type.URL) && route != null) {
                joiner.add(route.getPath());
            }
            if (types.contains(RateLimitProperties.Policy.Type.ORIGIN)) {
                joiner.add(rateLimitUtils.getRemoteAddress(request));
            }
            if (types.contains(RateLimitProperties.Policy.Type.USER)) {
                joiner.add(rateLimitUtils.getUser(request));
            }
        }

        // 添加索引
        joiner.add(String.valueOf(indexOf));

        return joiner.toString();

    }
}
