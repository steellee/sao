package com.lakala.sh.sao.demoa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class ZipkinTraceAController {

	/**
	 * AlwaysSampler：实现的isSampled方法始终返回true
	 * 调试时我们可以设置AlwaysSampler抽样机制来让每个跟踪信息都被收集
	 * 优先于配置的spring.sleuth.sampler.percentage，覆盖默认的PercentageBasedSampler策略
	 * @return
	 */
	/*@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}*/

	@Bean
//	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/tracea", method = RequestMethod.GET)
	public String trace() {
		log.info("===<call demoa-service>===");
//		return restTemplate().getForEntity("http://demob-service/traceb", String.class).getBody();
		return restTemplate().getForEntity("http://localhost:8503/traceb", String.class).getBody();
	}

}
