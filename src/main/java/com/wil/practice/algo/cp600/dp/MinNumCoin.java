package com.wil.practice.algo.cp600.dp;

public class MinNumCoin {
    private static int dpSolution(int amount, int[] coins) {
        if (amount<=0) return -1;
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for(int i = 1; i<=amount; i++) {
            for(int j = 0; j <coins.length; j++) {
                if (i-coins[j]>=0) dp[i] = Math.min(dp[i], dp[i-coins[j]])+1;
            }
        }
        return dp[amount]<Integer.MAX_VALUE? dp[amount]:-1;
    }

    public static void main(String[] args) {
        int amount = 5;
        int[]coins = new int[]{1, 2, 3, 4};
        System.out.println(dpSolution(amount, coins));
    }

}
