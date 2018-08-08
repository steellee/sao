package com.lakala.sh.sao.cmbc;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableSwagger2Doc        // 开启Swagger功能的注解
@EnableDiscoveryClient    // 激活Eureka中的DiscoveryClient实现，这样才能实现Controller中对服务信息的输出
//@ImportResource(locations = {"classpath:META-INF/ctx/ctx_main.xml"})	// xml加载相关beans配置
@ComponentScan(value = {"com.lakala.sh.sao"})
@SpringBootApplication
//@EnableSwagger2    // 启用Swagger2
//@EnableCaching	// 自动化配置合适的缓存管理器（MyCacheManager）
@EnableAsync	// 让@Async注解能够生效
@EnableOAuth2Client		// 启用Oauth2认证授权Web服务
public class CmbcServiceApplication extends SpringBootServletInitializer
		implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(CmbcServiceApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		/*String id = "s22";
		OrganInfoVO organInfoVO = new OrganInfoVO(id, "sagacity_" + id, "1", DateUtil.getNowTime(), "1", DateUtil.getNowTime(),1);
		organInfoService.add(organInfoVO);
//		OrganInfoVO organVO = organInfoService.loadById(id);
//		System.err.println(organVO.getOrganName());*/
	}
}
