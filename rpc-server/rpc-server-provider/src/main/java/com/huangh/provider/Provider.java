package com.huangh.provider;

import com.huangh.common.HelloService;
import com.huangh.common.IHelloService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Provider {

	public static void main(String[] args) {
		//SpringApplication.run(RpcServerProviderApplication.class, args);
		// 服务端实例化
		HelloService helloService = new HelloServiceImpl();

		// 服务发布
		RpcProxyServer proxyServer = new RpcProxyServer();
		proxyServer.publisher(helloService, 10000);
	}

}
