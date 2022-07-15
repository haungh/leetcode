package com.huangh.provider;

import com.huangh.common.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessHandlerThread implements Runnable {
    Socket socket;
    Object service;

    ProcessHandlerThread(Socket socket, Object service){
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        // 数据通信的处理
        try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
            RpcRequest request = (RpcRequest)ois.readObject(); // 获得数据，反序列化
            // 反射调用
            Object result = invoke(request);
            oos.writeObject(result);
            oos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws Exception{
        Class<?> clazz = Class.forName(rpcRequest.getClassName());
        Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
        return method.invoke(service, rpcRequest.getParams());
    }
}
