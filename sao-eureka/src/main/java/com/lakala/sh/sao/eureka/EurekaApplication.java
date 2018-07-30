package com.lakala.sh.sao.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka: 服务发现
 * java -jar sao-eureka-1.0.0-SNAPSHOT.jar --spring.profiles.active=node1
 * java -jar sao-eureka-1.0.0-SNAPSHOT.jar --spring.profiles.active=node2
 *
 */
@EnableEurekaServer		// 启动一个服务注册中心提供给其他应用进行对话
@SpringBootApplication
public class EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
}
