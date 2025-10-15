package com.wil.practice.algo.dp;

public class DominoTrominoTiling {

    private static final long MOD = 1000000007;

    private static int numTilings(int n) {
        long[][] dp = new long[n+1][3];
        dp[0][0] = 1;
        dp[1][0] =1;

        for (int i=2; i<=n; i++) {
            dp[i][0] = (dp[i-2][0] + dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-2][0] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-2][0] + dp[i-1][1]) % MOD;
        }
        return (int) dp[n][0];
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println(numTilings(n));
        int n2 = 5;
        System.out.println(numTilings(n2));
    }
}
