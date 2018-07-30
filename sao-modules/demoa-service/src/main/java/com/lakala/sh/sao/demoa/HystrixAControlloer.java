package com.lakala.sh.sao.demoa;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class HystrixAControlloer {

	@Value("${server.port}")
	String port;

	@RequestMapping("/helloA")
	@HystrixCommand(fallbackMethod = "hiError")
	public String helloA(@RequestParam(value = "name", defaultValue = "sao") String name) {
		return "hello " + name + " ,i am from port:" + port;
	}

	public String hiError(String name) {
		return "hello,"+name+",sorry,error!";
	}
}
