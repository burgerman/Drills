package com.wil.practice.proxy_aop.cglib;

public class TargetObject {
    public String method1(String paramName) {
        return paramName;
    }

    public int method2(int paramName) {
        return paramName;
    }

    public double method3(double paramName) {
        return paramName;
    }

    @Override
    public String toString() {
        return "TargetObject {}"+ this.getClass();
    }
}
