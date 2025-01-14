package com.wil.practice.designpattern.strategy;

import com.wil.practice.utils.FileUtils;

public class ProductUpdateSol<T> implements UpdateSol<T> {
    @Override
    public T[] update(T...data) {
        // Update
        T[] dataArr = data;
        int len = data.length;
        for (int v=0; v<len; v++) {
            System.out.println(dataArr[v]);
        }
        return dataArr;
    }
}
