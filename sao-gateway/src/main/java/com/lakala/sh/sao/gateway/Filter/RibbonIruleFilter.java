package com.lakala.sh.sao.gateway.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 灰度发布、蓝绿发布、金丝雀发布
 *
 * https://github.com/saleson
 */
@Configuration
public class RibbonIruleFilter extends ZuulFilter {
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // a filter has already forwarded
        // a filter has already determined serviceId
        return !ctx.containsKey(FORWARD_TO_KEY)
            && !ctx.containsKey(SERVICE_ID_KEY);
    }

    /**
     * 金丝雀发布:
     * 选择header里带有foo=1的全部路由到金丝雀服务节点上，其他的还走原来的老版本
     * todo 如果IRule配置了均衡负载，此处就失效
     * @return
     */
    @Override
    public Object run() {
        RibbonFilterContextHolder.clearCurrentContext();
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getParameter("version") != null) {
            // 金丝雀服务节点，需配置eureka.instance.metadata-map.lancher=1
            // put the serviceId in `RequestContext`
            RibbonFilterContextHolder.getCurrentContext()
                .add("lancher-version", "2.0");
        }  else {
            RibbonFilterContextHolder.getCurrentContext()
                .add("lancher-version", "1.0");
        }
        return null;
    }

}
