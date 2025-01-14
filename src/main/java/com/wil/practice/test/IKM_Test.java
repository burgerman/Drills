package com.wil.practice.test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IKM_Test{

    static int v2 = getValue();
    static int v1 = 10;

    static int getValue(){
        return v1;
    }

    static int sum() {
        return v1+v2;
    }

    static int subtract() {
        return v1-v2;
    }


    public static void main(String[] args) {
        System.out.println(sum());
        System.out.println(subtract());
        try (FileReader fr = new FileReader("")) {
            BufferedReader br = new BufferedReader(fr);
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
