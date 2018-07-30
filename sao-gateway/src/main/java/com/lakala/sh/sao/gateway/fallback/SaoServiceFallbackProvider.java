package com.lakala.sh.sao.gateway.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @project sao
 * @description 微服务sao-service停止服务，启用zuul路由熔断的容错与回退处理
 * @author steellee
 * @version 1.0.0
 *
 */
@Component
public class SaoServiceFallbackProvider implements FallbackProvider {

    // 指定为哪一个微服务提供回退
    @Override public String getRoute() {
        return "cmbc-service";
    }

    @Override public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override public int getRawStatusCode() throws IOException {
                // 返回数字类型的状态码
                return this.getStatusCode().value();
            }

            @Override public String getStatusText() throws IOException {
                // 返回状态文本
                return this.getStatusCode().getReasonPhrase();
            }

            @Override public void close() {

            }

            @Override public InputStream getBody() throws IOException {
                // 响应体
                return new ByteArrayInputStream("cmbc-service is unavailable, please try it again later!".getBytes());
            }

            @Override public HttpHeaders getHeaders() {
                // 设置header
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
