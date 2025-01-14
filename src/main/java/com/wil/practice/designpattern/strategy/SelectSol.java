package com.wil.practice.designpattern.strategy;

import java.util.List;

public interface SelectSol<T> {
    List<? extends String> select(T[] populatedData);

}
