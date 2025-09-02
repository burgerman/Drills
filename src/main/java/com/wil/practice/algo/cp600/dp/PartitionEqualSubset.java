package com.wil.practice.algo.cp600.dp;

public class PartitionEqualSubset {


    private static boolean subSetPartition(int[] nums) {
        int total = 0;

        for(int num : nums) {
            total += num;
        }
        if (total % 2 != 0) return false;

        int subTotal = total >> 1;
        boolean[][] dp = new boolean[nums.length+1][subTotal+1];
        for (int i =0; i<=nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i=1; i<=nums.length; i++) {
            for(int j=1; j<=subTotal; j++) {
                if (j>=nums[i-1]) {
                    // skip this element OR take this element
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                } else {
                    // skip this element
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[nums.length][subTotal];
    }

    public static void main(String[] args) {

        int[] testNums = {1, 5, 11, 5};
        int[] testNums2 = {1, 2, 3, 5};
        int[] testNums3 = {2, 2, 1, 1};
        System.out.println(subSetPartition(testNums));
        System.out.println(subSetPartition(testNums2));
        System.out.println(subSetPartition(testNums3));
    }
}
