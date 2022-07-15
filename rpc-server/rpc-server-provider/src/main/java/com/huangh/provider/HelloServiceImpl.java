package com.huangh.provider;


import com.huangh.common.HelloObject;
import com.huangh.common.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(HelloObject object) {
        return "这是调用的返回值，id=" + object.getId();
    }
}

