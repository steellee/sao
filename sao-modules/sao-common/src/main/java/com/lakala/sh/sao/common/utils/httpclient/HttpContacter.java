package com.lakala.sh.sao.common.utils.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Http 协议请求
 *
 * @author steellee
 * @version 1.0.0
 */
public class HttpContacter extends HttpContactAble {
	public HttpContacter(){
		
	}
	public HttpContacter(String contentType) {
		super(contentType);
	}

	public HttpContacter(int connectionTimeout, int socketTimeout) {
		this.setConnectionTimeout(connectionTimeout);
		this.setSocketTimeout(socketTimeout);
	}

	public HttpContacter(int connectionTimeout, int socketTimeout, String contentType) {
		super(contentType);
		this.setConnectionTimeout(connectionTimeout);
		this.setSocketTimeout(socketTimeout);
	}

	@Override
	protected HttpClient buildHttpClient() throws Exception {
		return HttpClients.createDefault();
	}
}
