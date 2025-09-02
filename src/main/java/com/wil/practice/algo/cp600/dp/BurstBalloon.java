package com.wil.practice.algo.cp600.dp;

public class BurstBalloon {

    private static int[][] memo;

    /**
     * Top-down solution
     */
    private static int maxVal(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n+2];
        newNums[0] = newNums[n+1] = 1;
        for (int i = 0; i<n; i++) newNums[i+1] = nums[i];
        memo = new int[n+2][n+2];
        return dfs(newNums, 0, n+1);
    }

    private static int dfs(int[] coins, int left, int right) {
        if(right-left == 1) return 0;
        if(memo[left][right] != 0) return memo[left][right];

        int max = 0;
        for (int i = left+1; i<right; i++) {
            int currentVal = coins[left] * coins[i] * coins[right];
            int total = dfs(coins, left, i) + dfs(coins, i, right) + currentVal;
            max = Math.max(total, max);
        }
        memo[left][right] = max;
        return max;
    }


    /**
     * Bottom-up solution
     */

    private static int maxValBottomUp(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n+2];
        newNums[0] = newNums[n+1] = 1;
        for (int i=0; i<n; i++) newNums[i+1] = nums[i];

        int[][] dp = new int[n+2][n+2];

        // start from length 2
        for (int len = 2; len<=n+1; len++) {
            for (int left = 0; left<= n-len+1; left++) {
                int right = left+len;
                int max = 0;
                for (int k=left+1; k<right; k++) {
                    int currentVal = newNums[left] * newNums[right] * newNums[k];
                    int total = dp[left][k] + dp[k][right] + currentVal;
                    max = Math.max(total, max);
                }
                dp[left][right] = max;
            }
        }

        return dp[0][n+1];
    }


    public static void main(String[] args) {
        System.out.println(maxValBottomUp(new int[]{3,1,5,8}));
        System.out.println(maxVal(new int[]{3,1,5,8})); // 167
                    // 1, 3, 1, 5, 8, 1
                    // 0  1  2  3  4  5
    }

}
