package com.wil.practice.algo.cp600.dc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class TopKFrequent {

    private static int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        Arrays.sort(nums);
        int n = nums.length;
        int index=0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2)->arr2[1] - arr1[1]);
        while (index<n) {
            int num = nums[index];
            int count = 0;
            while (index<n && nums[index] == num) {
                count++;
                index++;
            }
            pq.add(new int[]{num, count});
        }
        List<Integer> res = new ArrayList<>();
        for (int i =0; i<k; i++) {
            if(!pq.isEmpty()) {
                res.add(pq.poll()[0]);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

}
