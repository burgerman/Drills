package com.wil.practice.algo.dp;

import java.util.Arrays;

public class ArrayPartition {

    public static void main(String[] args) {
        int[] arr = {1,2,5};
        System.out.println(partitionArrV2(arr));
    }


    private static boolean partitionArr(int[] arr) {
        if (arr.length - 2 < 0) {
            return false;
        }
        int sum = Arrays.stream(arr).sum();
        if((sum&1) != 0) {
            return false;
        }
        int subArrSum = sum/2;
        boolean[][] dp = new boolean[arr.length][subArrSum+1];
        if(arr[0] <= subArrSum) {
            dp[0][0] = true;
        }
        for(int i=1; i<arr.length; i++) {
            for(int j=0; j<=subArrSum; j++) {

                if(arr[i] == j) {
                    // 当某个元素恰好满足分区值
                    dp[i][j] = true;
                    continue;
                } else if (arr[i] < j) {
                    // 不选择arr[i] -> dp[i-1][j], 在[0, i-1]区间内已有部分元素和为j, dp[i][j]已为true
                    // 选择 arr[i] -> dp[i-1][j-arr[i]], 在[0, i-1]区间内找到部分元素和为j-arr[i]
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[arr.length-1][subArrSum];
    }


    private static boolean partitionArrV2(int[] arr) {
        if (arr.length - 2 < 0) {
            return false;
        }
        int sum = Arrays.stream(arr).sum();
        if((sum&1) != 0) {
            return false;
        }
        int subArrSum = sum/2;
        boolean[] dp = new boolean[subArrSum+1];
        if(arr[0] <= subArrSum) {
            dp[0] = true;
        }
        for(int i=0; i<arr.length; i++) {
            for(int j=subArrSum; j>=0; j--) {
                if(j-arr[i]>=0) {
                    // 不选择arr[i] -> dp[i-1][j], 在[0, i-1]区间内已有部分元素和为j, dp[i][j]已为true
                    // 选择 arr[i] -> dp[i-1][j-arr[i]], 在[0, i-1]区间内找到部分元素和为j-arr[i]
                    dp[j] = dp[j] || dp[j-arr[i]];
                }
            }
        }
        return dp[subArrSum];
    }

}
