package com.wil.practice.proxy_aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TargetInterceptor implements MethodInterceptor {
    /**
     * 重写方法拦截在方法前和方法后加入业务
     * Object object为目标对象
     * Method method为目标方法
     * Object[] params为参数，
     * MethodProxy methodProxy CGlib方法代理对象
     */
    @Override
    public Object intercept(Object object, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before Calling");
        Object result = methodProxy.invokeSuper(object, params);
        System.out.println("After Calling "+result);
        return result;
    }
}
