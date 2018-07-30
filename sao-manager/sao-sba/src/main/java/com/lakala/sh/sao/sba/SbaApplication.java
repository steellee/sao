package com.lakala.sh.sao.sba;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * spring-boot-admin:
 * code: https://github.com/codecentric/spring-boot-admin
 * doc: http://codecentric.github.io/spring-boot-admin/2.0.1/#getting-started
 * 		http://codecentric.github.io/spring-boot-admin/1.5.7/#getting-started (任支持整合Hystrix，Turbine，Activiti UI Module)
 */
@EnableAdminServer		// 启用Admin模块
@EnableDiscoveryClient	// 用于服务注册和发现
@SpringBootApplication
public class SbaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbaApplication.class, args);
	}
}
