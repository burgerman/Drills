package com.wil.practice.algo.integers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicate {


    private static List<Integer> findDup(List<Integer> integers) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (Integer num : integers) {
            if (!set.add(num)) {
                duplicates.add(num);
            }
        }
        return new ArrayList<>(duplicates);
    }

}
