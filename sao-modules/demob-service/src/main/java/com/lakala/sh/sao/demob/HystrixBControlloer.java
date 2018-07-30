package com.lakala.sh.sao.demob;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HystrixBControlloer {

	@Value("${server.port}")
	String port;

	@RequestMapping("/helloB")
	@HystrixCommand(fallbackMethod = "hiError")
	public String helloB(@RequestParam(value = "name", defaultValue = "sao") String name) throws Exception {
		Thread.sleep(10000);
		return "hello " + name + " ,i am from port:" + port;
	}

	public String hiError(String name) {
		return "hello,"+name+",sorry,error!";
	}
}
