package com.wil.practice.algo.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class AmazonShortestSub862 {

    private static int shortestSubarray(int[] nums, int k) {
        int len = nums.length;
        long[] sums = new long[len+1];
        for (int i=0; i<len; i++) {
            sums[i+1] = sums[i] + nums[i];
        }
        //使用单调队列
        Deque<Integer> queue = new ArrayDeque();
        int shortest = len+1;
        for(int i =0; i<=len; i++) {
            long tmp = sums[i];
            // 当前遍历到i，设queue中遍历过的最小下标为j, 若sums[i] - sums[j]>=k，
            // 则j不可能作为子数组的起点，起点应在 (j,i] 开区间内。所以将j踢出
            while(!queue.isEmpty() && tmp - sums[queue.peekFirst()] >=k) {
                shortest = Math.min(shortest, i-queue.pollFirst());
            }
            // 在j<i的前提下，如果s[i]<=s[j], 设可与s[j]组成满足条件的子数组的数为x
            // 如果x-s[j]>=k, 则x-s[i]>=k, 而s[i]与x的下标距离更近，故此s[j]可踢出
            //queue.peekLast()获取当前s[j]
            while (!queue.isEmpty() && sums[queue.peekLast()]>=tmp) {
                queue.pollLast();
            }
            //将s[i]加入
           queue.add(i);
        }
        return shortest<=len? shortest:-1;
    }

    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{2,-1,2}, 3));
    }
}
