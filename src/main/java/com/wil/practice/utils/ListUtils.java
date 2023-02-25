package com.wil.practice.utils;

import java.util.Collection;

public class ListUtils {

    public static Collection<?> retainCommonElements(Collection<?> source, Collection<?> comparison) {
        source.retainAll(comparison);
        return source;
    }

}
