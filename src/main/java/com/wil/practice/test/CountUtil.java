package com.wil.practice.test;

import java.util.*;

public class CountUtil {

    public static Map<String, Integer> count (List<String> urls) {
        if( urls!=null && urls.size()>0 ) {
                Map<String, Integer> map = new HashMap<>(16384);
                urls.forEach(o-> map.compute(o,(key, val) -> (val==null)?1:val+1));
                return map;
        }
        return null;
    }

    public static Map.Entry<String,Integer> mostFrequentlyAccessedUrl(Map<String, Integer> map) {
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue()-o1.getValue());
        return list.get(0);
    }


    public static void main(String[] args) {
        String[] arr = new String[]{"a", "b", "c", "b", "a", "a", "a"};
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        Map<String, Integer> map = count(list);
        Map.Entry<String,Integer> urlObj = mostFrequentlyAccessedUrl(map);
        System.out.println("Url: "+urlObj.getKey()+", times: "+urlObj.getValue());
    }
}
