package com.wil.practice.proxy_aop;

public class MyInterfaceImpl implements MyInterface {
    @Override
    public void doTest(String param1, String param2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Param1: ").append(param1).append(", Param2: ").append(param2);
        System.out.println(stringBuilder.toString());
    }
}
