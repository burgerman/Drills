package com.wil.practice.proxy_aop;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public String shoot() {
        String result = "Hey! Yo man!";
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        // Test JDK proxy acts for a plain java class to invoke one of its methods
        JDKProxyFactory j1 = new JDKProxyFactory(new Test());
        try {
            j1.invoke(null, Test.class.getDeclaredMethod("shoot", null), null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        //Test JDK proxy acts for an interface to invoke one of its implementations' methods
        JDKProxyFactory j2 = new JDKProxyFactory(new MyInterfaceImpl());
        try {
            j2.invoke(null, MyInterface.class.getDeclaredMethod("doTest", String.class, String.class), new Object[]{"Name","Age"});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        // JDK Proxy
        Class[] interfaces = {MyInterface.class};
        MyInterface myInterface = new MyInterfaceImpl();
        //Pass principal(被代理) object into
        MyInterface proxyInstance = (MyInterface) Proxy.newProxyInstance(Test.class.getClassLoader(), interfaces, new JDKProxyFactory(myInterface));
        String result = proxyInstance.doTest("Email","Account");
        // Test get all declared methods of the class by JDK reflection
        try {
            Class cl = Class.forName(Test.class.getName());
            Method[] methods = cl.getDeclaredMethods();
            for(Method method : methods) {
                System.out.println(method.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
