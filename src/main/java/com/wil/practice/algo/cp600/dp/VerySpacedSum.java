package com.wil.practice.algo.cp600.dp;

public class VerySpacedSum {

    private static int getMaxSum(int[] arr) {
        int maxVal = Integer.MIN_VALUE;
        if(arr.length<4) {
            if(arr.length<1) return maxVal;
            for (int i = 0; i < arr.length; i++) maxVal = Math.max(maxVal, arr[i]);
            return maxVal;
        }
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(i<3) {
                dp[i] = Math.max(dp[i-1], arr[i]);
            } else {
                dp[i] = Math.max(dp[i-1], arr[i]+dp[i-3]);
            }
            maxVal = Math.max(maxVal, dp[i]);
        }
        return maxVal;
    }

    public static void main(String[] args) {

        int[] arr = {4,6,7,1,5};
        System.out.println(getMaxSum(arr));
    }

}
