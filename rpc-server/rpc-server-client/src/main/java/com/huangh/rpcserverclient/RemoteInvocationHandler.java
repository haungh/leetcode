package com.huangh.rpcserverclient;

import com.huangh.common.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    private final String host;
    private final int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 执行代理， 触发远程调用
        // 1。 组装参数
        // 2。 序列化
        // 3。 进行网络传输
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParams(args);
        request.setTypes(method.getParameterTypes());
        // 开始传输
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);

        return rpcNetTransport.send(request);
    }
}
