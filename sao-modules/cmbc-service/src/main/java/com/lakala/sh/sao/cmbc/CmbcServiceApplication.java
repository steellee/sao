package com.lakala.sh.sao.cmbc;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableSwagger2Doc        // 开启Swagger功能的注解
//@EnableDiscoveryClient    // 激活Eureka中的DiscoveryClient实现，这样才能实现Controller中对服务信息的输出
//@ImportResource(locations = {"classpath:META-INF/ctx/ctx_main.xml"})	// xml加载相关beans配置
@ComponentScan(value = {"com.lakala.sh.sao"})
@SpringBootApplication
//@ServletComponentScan	//扫描Servlet
//@MapperScan("com.lakala.sh.sao.cmbc.demo.mapper")//必须加这个，不加报错，如果不加，也可以在每个mapper上添加@Mapper注释，并且这里还要多填一个注释
//@Import(SpringUtil.class)

//@EnableSwagger2    // 启用Swagger2
//@EnableCaching	// 自动化配置合适的缓存管理器（MyCacheManager）
@EnableAsync	// 让@Async注解能够生效
public class CmbcServiceApplication //        extends SpringBootServletInitializer
		extends SpringBootServletInitializer
		implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(CmbcServiceApplication.class, args);
	}

	/*protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(LightUpmsApplication.class);
	}*/

	/*@Bean
	public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
		return new ResourceUrlEncodingFilter();
	}*/

	@Override
	public void run(String... arg0) throws Exception {
		/*String id = "s22";
		OrganInfoVO organInfoVO = new OrganInfoVO(id, "sagacity_" + id, "1", DateUtil.getNowTime(), "1", DateUtil.getNowTime(),1);
		organInfoService.add(organInfoVO);
//		OrganInfoVO organVO = organInfoService.loadById(id);
//		System.err.println(organVO.getOrganName());*/
	}

	/*@Override
	protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(LightUpmsApplication.class);
	}*/

	/**
	 * 新增的servlet设置其请求路径,加载自定义testservlet4配置
	 */
	/*@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("启动加载自定义的MyServletContainerInitializer");
		System.out.println("启动加载自定义的MyServletContainerInitializer");
		ServletRegistration.Dynamic testServlet=servletContext.addServlet("servlet4","com.shf.springboot.servlet.testservlet4");
		testServlet.setLoadOnStartup(1);
		testServlet.addMapping("/testservlet4");
	}*/
}
