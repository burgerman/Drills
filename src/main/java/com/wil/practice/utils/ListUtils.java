package com.wil.practice.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {

    public static Collection<?> retainCommonElements(Collection<?> source, Collection<?> comparison) {
        source.retainAll(comparison);
        return source;
    }

    private static int sumAllElements(List<Integer> list) {
//      return list.stream().mapToInt(e->e).sum();
        return list.stream().mapToInt(e->e).reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        System.out.println(sumAllElements(Arrays.asList(new Integer[]{1,2,3,4})));
    }

}
