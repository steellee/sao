package com.lakala.sh.sao.common.utils.httpclient.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.http.conn.ConnectTimeoutException;

/**
 *
 * @author steellee
 * @version 1.0.0
 */
public class SSLSocketFactory implements ProtocolSocketFactory {
	private boolean isChkCert;

	public SSLSocketFactory(boolean isChkCert) {
		this.isChkCert = isChkCert;
	}

	public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException, Exception {
		return SSLContextFactory.getInstance(isChkCert).getSocketFactory().createSocket(socket, host, port, autoClose);
	}

	public Socket createSocket(String host, int port, InetAddress localAddress, int localPort, HttpConnectionParams params)
			throws IOException, UnknownHostException, ConnectTimeoutException {
		if (params == null) {
			throw new IllegalArgumentException("Parameters may not be null!");
		}
		int timeout = params.getConnectionTimeout();
		SocketFactory socketfactory = SSLContextFactory.getInstance(isChkCert).getSocketFactory();
		if (timeout == 0) {
			return socketfactory.createSocket(host, port, localAddress, localPort);
		}
		Socket socket = socketfactory.createSocket();
		SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
		SocketAddress remoteaddr = new InetSocketAddress(host, port);
		socket.bind(localaddr);
		socket.connect(remoteaddr, timeout);
		return socket;
	}

	public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort)
			throws IOException, UnknownHostException, ConnectTimeoutException {
		return SSLContextFactory.getInstance(isChkCert).getSocketFactory().createSocket(host, port, clientHost, clientPort);
	}

	public Socket createSocket(String host, int port) throws IOException, UnknownHostException, ConnectTimeoutException {
		return SSLContextFactory.getInstance(isChkCert).getSocketFactory().createSocket(host, port);
	}
}
