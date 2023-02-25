package com.wil.practice.utils;

import java.util.HashMap;
import java.util.Map;

public class MapTools {

    public static boolean countTimes (Map<String, Integer> map, String key) {
        int i = map.get(key);
        map.compute(key, (k, v) -> (v==null)? 1:v+1);
        i = map.get(key)-i;
        if(i>0) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(16);
        String key1 = "hey";
        String key2 = "hello";
        // Create k-v if there's no record
        map.computeIfAbsent(key2, k-> map.get(key2)+1);

        // Return the existing value of record if there's been a k-v pair
        map.computeIfAbsent(key2, k->0);

        // Update the existing value of record to a new one
        map.computeIfPresent(key2, (k,v)-> 0);
    }

}
