package com.huangh.rpcserverclient;

import com.huangh.common.HelloObject;
import com.huangh.common.HelloService;
import com.huangh.common.IHelloService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpcServerClientApplication {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 10000;
		//SpringApplication.run(RpcServerClientApplication.class, args);
		// 1. 动态代理

		// 一大段的网络逻辑
		RpcClientProxy rpcClientProxy = new RpcClientProxy();
		HelloService helloService = rpcClientProxy.getProxy(HelloService.class, host, port);
		String s = helloService.hello(new HelloObject(1, "huanghe"));
		System.out.println(s);
	}

}
