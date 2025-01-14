package com.wil.practice.designpattern.strategy;

import java.util.ArrayList;
import java.util.List;

public class ProductSelectSol<T> implements SelectSol<T>{

    @Override
    public List<? extends String> select(T[] populatedData) {
        // retrieve based on the populated data
        List list = new ArrayList(populatedData.length);
        for(T i:populatedData) {
           if(!String.valueOf(i).isBlank() && !String.valueOf(i).isEmpty()) {
               list.add(i);
           }
        }
        return list;
    }
}
