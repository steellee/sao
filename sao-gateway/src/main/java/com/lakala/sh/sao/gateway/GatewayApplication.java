package com.lakala.sh.sao.gateway;

import com.lakala.sh.sao.gateway.config.RibbonConfiguration;
import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Zuul服务网关:服务路由、均衡负载功能之外，它还具备了权限控制等功能
 * 解决问题：
 * 1，破坏了服务集群中REST API无状态特点，污染整个开放服务的业务逻辑，
 * 2，无法直接复用既有接口，需要在原有接口上增加校验逻辑，或者增加一个代理调用来实现权限控制
 *
 *
 * Zuul通过与Spring Cloud Eureka的整合，实现了对服务实例的自动化维护，
 * 所以在使用服务路由配置的时候，我们不需要向传统路由配置方式那样为serviceId去指定具体的服务实例地址，
 * 只需要通过一组zuul.routes.<route>.path与zuul.routes.<route>.serviceId参数对的方式配置即可
 *
 */
@EnableEurekaClient		// 让 Zuul 能够访问 Eureka Server 并注册自身
@EnableZuulProxy		// 开启Zuul的功能,包含@EnableZuulServer + route过滤器
@EnableSwagger2Doc		// 开启Swagger功能的注解
@SpringBootApplication
@RibbonClients(value = {
	@RibbonClient(name = "cmbc-service", configuration = RibbonConfiguration.class)
})
public class GatewayApplication {

	@PostConstruct
	public void zuulInit() {
		FilterLoader.getInstance().setCompiler(new GroovyCompiler());
		// 读取配置，获取Java下脚本根目录
		String scriptRoot = System.getProperty("zuul.filter.root", "sao-gateway/src/main/java/groovy/filters");
		// 获取刷新间隔
		String refreshInterval = System.getProperty("zuul.filter.refreshInterval", "5");
		if (scriptRoot.length() > 0) scriptRoot = scriptRoot + File.separator;
		try {
			FilterFileManager.setFilenameFilter(new GroovyFileFilter());
			// 指定filter下pre/route/post三个目录
			FilterFileManager.init(Integer.parseInt(refreshInterval), scriptRoot + "pre",
				scriptRoot + "route", scriptRoot + "post");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
