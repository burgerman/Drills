package com.wil.practice.designpattern.strategy;

public class TestStrategy {

    public static void main(String[] args) {
        IntegerSol is = new IntegerSol();
        is.setSelectSol(new ProductSelectSol<Integer>());
        is.setUpdateSol(new ProductUpdateSol<Integer>());
        Integer[] response = is.evaluate(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(response);

        DoubleSol ds = new DoubleSol();
        ds.setSelectSol(new ProductSelectSol<Double>());
        ds.setUpdateSol(new ProductUpdateSol<Double>());
        Double[] doubleResponse = ds.evaluate(new Double[]{1.1, 2.2, 3.3});
        System.out.println(doubleResponse);

        StringSol ss = new StringSol();
        ss.setSelectSol(new ProductSelectSol<String>());
        ss.setUpdateSol(new ProductUpdateSol<String>());
        String[] stringResponse = ss.evaluate(new String[]{"data1", "data2", "data3"});
        System.out.println(stringResponse);
    }


}
