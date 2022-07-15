package com.huangh.provider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer {

    /**
     * @param service   发布哪个服务
     * @param port      发布端口
     */
    public void publisher(Object service, int port){
        // 使用 线程池技术
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // BIO  一次只能做一个，吞吐量低
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true){     // 不断监听客户端连接
                // 两次阻塞
                Socket socket = serverSocket.accept();// 等待客户端连接, 获得传入参数   建立通信阻塞
                /**
                 * 线程池非阻塞，提高吞吐量
                 */
                executorService.execute(new ProcessHandlerThread(socket, service));
                // 数据从网卡到内存，
               // InputStream is = socket.getInputStream();  // IO阻塞 客户端没有数据输入也会阻塞
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
