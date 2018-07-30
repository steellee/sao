package com.lakala.sh.sao.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 访问：http://localhost:8401/hystrix
 * 监测加入：http://localhost:8401/turbine.stream
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine    // 开启turbine
public class TurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurbineApplication.class, args);
	}
}
