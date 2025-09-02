package com.wil.practice.algo.prefix_sum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HasGoodSubArray {


    private static boolean checkSubarraySum(int[] nums, int k) {
        boolean res = false;
        if (nums.length < 2) return res;
        int count=1;
        int i,j, sum;
        while (count<nums.length) {
            i = 0;
            j = i+count;
            while (j<nums.length) {
                sum = 0;
                for (int t=j; t>=i; t--) {
                    sum += nums[t];
                }
                if (sum%k==0) return true;
                i++;
                j = i+count;
            }
            count++;
        }
        return false;
    }


    private static boolean checkSubarraySumOptimized(int[] nums, int k) {
        if (nums.length < 2) return false;
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i=1; i<nums.length; i++) sums[i] = sums[i-1] + nums[i];
        int l=1, r=nums.length-1;
        while (l<r) {
            if((sums[r] - sums[l - 1]) % k == 0) {
                return true;
            }
            l++;
        }

        l=0;
        r=nums.length-1;
        while (l<r) {
            if((sums[r] - nums[r]) % k == 0) {
                return true;
            }
            r--;
        }

        l=1;
        r=nums.length-2;
        while (l<r) {
            if((sums[r] - sums[l - 1]) % k == 0) {
                return true;
            }
            l++;
            r--;
        }
        return false;
    }


    private static boolean optimalSolution(int[] nums, int k) {

        Map<Integer, Integer> remainderIndex = new HashMap<>();
        remainderIndex.put(0, -1); // remainder 0 seen at index -1 (before array starts)

        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            if (k != 0) { // avoid division by zero
                prefixSum %= k;
            }

            if (remainderIndex.containsKey(prefixSum)) {
                int prevIndex = remainderIndex.get(prefixSum);
                if (i - prevIndex > 1) { // length >= 2
                    return true;
                }
            } else {
                remainderIndex.put(prefixSum, i);
            }
        }

        return false;
    }



    public static void main(String[] args) {
        int[] nums = {23,2,4,6,6};
        int k = 7;

        System.out.println(checkSubarraySumOptimized(nums, k));
        System.out.println(checkSubarraySum(nums, k));
        System.out.println(optimalSolution(nums, k));
    }

}
