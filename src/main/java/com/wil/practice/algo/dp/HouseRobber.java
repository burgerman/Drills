package com.wil.practice.algo.dp;

public class HouseRobber {

    private static int rob(int[] nums) {
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        int max = dp[1];
        for (int i=2; i<= nums.length; i++) {
            dp[i] = Math.max(dp[i-2]+nums[i-1], dp[i-1]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,1};
        System.out.println(rob(nums));
        int[] nums2 = new int[]{2,7,9,3,1};
        System.out.println(rob(nums2));
    }


}
