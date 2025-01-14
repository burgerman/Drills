package com.wil.practice.proxy_aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JDKProxyFactory implements InvocationHandler {

    private Object obj;

    public JDKProxyFactory(Object obj) {
        this.obj = obj;
    }

    public Object getProxy() {
        Object object = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
        return object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // pre-processing
        System.out.println("Before: "+method.getName()+"; "+ Arrays.toString(args));
        // enhanced method
        Object returnVal = method.invoke(obj, args);
        // post-processing
        System.out.println("After: "+obj);
        return returnVal;
    }
}
