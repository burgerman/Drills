package com.wil.practice.utils;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class SkipListUtils {

    private static Map<Object, Object> getSortedMap(Map<Object, Object> map) {

        if(map == null) {
            return null;
        }
        // ConcurrentSkipListMap在未指定comparator时，存入元素按照自然顺序存储
        // 默认排序String对象会按照字典升序
        // 按照降序顺序
        ConcurrentSkipListMap<Object, Object> cslm = new ConcurrentSkipListMap<>((o1, o2) -> ((Comparable) o2).compareTo(o1));
        cslm.putAll(map);
        // 采用降序: cslm.descendingMap();
        return cslm;
    }

    private static List<Map.Entry<Object, Object>> getSortedList(Map<Object, Object> map) {
        if(map==null) {
            return null;
        }
        return map.entrySet().stream().sorted((o1,o2)->((Comparable)o1.getValue()).compareTo(o2.getValue())).collect(Collectors.toList());
    }

    private static <T> Set<T> convertList2Set (List<T> list) {
        // return new HashSet<>(list);
        return list.stream().collect(Collectors.toSet());
    }

    private static <T> List<T> convertSet2List (Set<T> set) {
        return set.stream().collect(Collectors.toList());
    }

    private static boolean disjoint(Collection<?> c1, Collection<?> c2) {
        return Collections.disjoint(c1, c2);
    }

}
