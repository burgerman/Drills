package com.wil.practice.strategy;

public class Test {
    public static void main(String[] args) {

        Handler handler = HandlerHolder.getHandler("A");
        handler.printType("Wilfried");
    }

}
