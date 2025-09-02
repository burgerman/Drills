package com.wil.practice.algo.monotonic;

import java.util.*;
import java.util.stream.Collectors;

public class MonotonicQueue496 {
    private static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> queue = new ArrayDeque<>();
        int index;
        outer: for(int i=0; i<nums1.length; i++) {
             index = 0;
            while (index < nums2.length) {

                if(nums1[i] == nums2[index]) {
                   while(index < nums2.length) {
                       if(nums1[i] < nums2[index]) {
                           queue.add(nums2[index]);
                           continue outer;
                       }
                       index++;
                   }

                } else {
                    index++;
                }
            }
            queue.add(-1);
        }
        return queue.stream().mapToInt(Integer::intValue).toArray();
    }


    private static int[] nextGreaterElementOptimized(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Map<Integer, Integer> dict = new HashMap<>(nums2.length);
        for(int i =0; i<nums2.length; i++) {
            dict.put(nums2[i], i);
        }
        List<Map.Entry> list =dict.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).collect(Collectors.toList());
        outer: for(int j=0; j<nums1.length; j++) {
            int index;
            int next;
            if(dict.containsKey(nums1[j])) {
                index = ((Integer)list.get(dict.get(nums1[j])).getValue()).intValue();
                 while(index< nums2.length) {
                     next = ((Integer)list.get(index).getKey()).intValue();
                     if(nums1[j]< next){
                         result[j] = next;
                         continue outer;
                     }
                    index++;
                 }
            }
            result[j] = -1;
        }
        return result;
    }

    private static int[] nextGreaterMonotonicStack(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> dict = new HashMap<>(nums2.length);
        int tmp;
        for(int i=nums2.length-1; i>=0; i--) {
            tmp = nums2[i];
            while(!stack.isEmpty() && tmp>stack.peek()) {
                stack.pop();
            }
            dict.put(tmp, stack.isEmpty()?-1:stack.peek());
            stack.push(tmp);
        }
        int[] result = new int[nums1.length];
        Arrays.fill(result, -1);
        for(int i =0; i<nums1.length; i++) {
            if(dict.containsKey(nums1[i])) result[i] = dict.get(nums1[i]);
        }
        return result;
    }


}
