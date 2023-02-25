package com.wil.practice.classload;

public class ClassLoadTest {
    private static SubClass sc = new SubClass();
    public static void main(String[] args) {
        sc.call();
    }
}

class ParentClass{

    static int val = 10;
    static {
        System.out.println("Init parent class");
    }
    public ParentClass() {
        System.out.println("Init parent class instance");
    }
}

class SubClass extends ParentClass {

    static {
        System.out.println("Init sub class");
    }

    public SubClass() {
        System.out.println("Init an instance of the sub class");
    }

    public void call() {
        System.out.println("Hi");
    }
}