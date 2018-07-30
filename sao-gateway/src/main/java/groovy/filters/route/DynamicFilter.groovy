package groovy.filters.route

import com.netflix.zuul.ZuulFilter
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants

/**
 * 测试用
 */
class DynamicFilter extends ZuulFilter {

	public boolean shouldFilter() {
		return true;
	}

	public Object run() {
		System.out.println("=========  这一个是动态加载的过滤器：DynamicFilter");
		return null;
	}

	public String filterType() {
		return FilterConstants.ROUTE_TYPE;
	}

	public int filterOrder() {
		return 3;
	}
}
