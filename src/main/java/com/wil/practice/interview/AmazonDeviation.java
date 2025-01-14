package com.wil.practice.interview;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AmazonDeviation {


    private static int maxDeviationInSubStrings(String str) {
        int max=0;
        Map<String, Integer> dict = new HashMap<>();
        for(int i=0; i<str.length(); i++) {
            for(int j=i+1; j<str.length(); j++) {
                String subStr = str.substring(i, j);
                List<Character> list;
                Map<Character, Integer> charCount;
                List<Map.Entry<Character, Integer>> res;
                int deviation;
                if (!dict.containsKey(subStr)) {
                    list = subStr.chars().mapToObj(e->(char)e).collect(Collectors.toList());
                    charCount = list.stream().collect(Collectors.groupingBy(c->c, Collectors.counting()))
                            .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().intValue()));
                    res = charCount.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).collect(Collectors.toList());
                    deviation = res.get(res.size()-1).getValue()-res.get(0).getValue();
                    dict.put(subStr,deviation);
                    max = Math.max(max, deviation);
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(maxDeviationInSubStrings("caaacbb"));

    }
}
