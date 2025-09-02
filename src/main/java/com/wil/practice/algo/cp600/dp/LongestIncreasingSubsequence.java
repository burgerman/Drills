package com.wil.practice.algo.cp600.dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {


    private static int findLIS(int[] nums) {
        int n = nums.length;
        if (n<1) return 0;
        int[] dp = new int[n];
        int len = 0;
        Arrays.fill(dp, 1);
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                if (nums[j]<nums[i]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
            len = Math.max(len, dp[i]);
        }
        return len;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums1 = {0,1,0,3,2,3};
        int[] nums2 = {7,7,7,7,7,7,7};
        System.out.println(findLIS(nums));
        System.out.println(findLIS(nums1));
        System.out.println(findLIS(nums2));
    }

}
