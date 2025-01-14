package com.wil.practice.proxy_aop.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class TestCGLib {

    public static void main(String[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        CallbackFilter callbackFilter = new TargetObjMethCallbackFilter();

        Callback nope = NoOp.INSTANCE;
        Callback cb1 = new TargetInterceptor();
        Callback cb2 = new TargetResultFixed();
        enhancer.setCallbacks(new Callback[]{nope, cb1, cb2});
        enhancer.setCallbackFilter(callbackFilter);
        TargetObject targetInterceptor = (TargetObject) enhancer.create();
        System.out.println(targetInterceptor);
        System.out.println(targetInterceptor.method1("mth1"));
        System.out.println(targetInterceptor.method2(22));
        System.out.println(targetInterceptor.method3(33.0));
    }

}
