package com.lakala.sh.sao.demob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix                // 开启断路器
@EnableHystrixDashboard        // 开启HystrixDashboard
public class DemoBApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBApplication.class, args);
	}

}
