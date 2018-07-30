package com.lakala.sh.sao.demob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class ZipkinTraceBController {

	/**
	 * X-B3-TraceId：一条请求链路（Trace）的唯一标识，必须值
	 X-B3-SpanId：一个工作单元（Span）的唯一标识，必须值
	 X-B3-ParentSpanId:：标识当前工作单元所属的上一个工作单元，Root Span（请求链路的第一个工作单元）的该值为空
	 X-B3-Sampled：是否被抽样输出的标志，1表示需要被输出，0表示不需要被输出
	 X-Span-Name：工作单元的名称
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/traceb", method = RequestMethod.GET)
	public String trace(HttpServletRequest request) {
		log.info("===<call demob-service, TraceId={}, SpanId={}, ParentSpanId={}, Sampled={},Span-Name={}>===",
			request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"), request.getHeader("X-B3-ParentSpanId"), request.getHeader("X-B3-Sampled"), request.getHeader("X-Span-Name"));
		return "TraceB";
	}
}
