package com.wil.practice.proxy_aop.cglib;

import net.sf.cglib.proxy.FixedValue;

public class TargetResultFixed implements FixedValue {
    //表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值
    @Override
    public Object loadObject() throws Exception {
        System.out.println("Fixed output: ");
        Object obj = -999;
        return obj;
    }
}
