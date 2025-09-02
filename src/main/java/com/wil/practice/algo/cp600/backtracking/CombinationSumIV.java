package com.wil.practice.algo.cp600.backtracking;

import java.util.*;

public class CombinationSumIV {


    private static int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        if (nums[0]>target) return 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        dfs(nums, target, prefixSum);
        return prefixSum.get(target);
    }


    private static int dfs(int[] nums, int target, Map<Integer, Integer> prefixSum) {
        if (target<0) return 0;
        if (target == 0) return 1;
        if(prefixSum.containsKey(target)) return prefixSum.get(target);
        int count = 0;
        for (int num : nums) {
            if (num>target) break;
            count += dfs(nums, target-num, prefixSum);
        }
        prefixSum.put(target, count);
        return count;
    }



    public static int DPSolution(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // For each target value from 1 to target
        for (int i = 1; i <= target; i++) {
            // Try each number
            for (int num : nums) {
                if (num <= i) dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int target = 4;
        System.out.println(combinationSum4(nums, target));
        System.out.println(DPSolution(nums, target));
    }

}
