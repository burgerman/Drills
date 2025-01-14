package com.wil.practice.designpattern;

import com.wil.practice.designpattern.builderpattern.Computer;

import java.util.Locale;

public class TestPatterns {
    public static void main(String[] args) {
        Computer c = new Computer.Builder("M2", "256GB")
                .setKeyboard("Magic 3")
                .setUsbCount(2)
                .setDisplay("Apple")
                .build();

        Computer c2 = new Computer.Builder("Intel9", "512GB").setUsbCount(4).build();
        System.out.println("Created computer: "+c.toString());
        System.out.println("Created computer: "+c2.toString());
        Locale locale = new Locale("USA");
        System.out.println(locale.getCountry());
    }

}
