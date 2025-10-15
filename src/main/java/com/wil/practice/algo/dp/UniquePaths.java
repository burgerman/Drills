package com.wil.practice.algo.dp;

public class UniquePaths {

    private static int uniquePaths(int m, int n) {
        if (m==n && m==1) return 1;
        int[][] dp = new int[m+1][n+1];
        dp[1][1] = 1;
        for (int i=2; i<=m; i++) {
            dp[i][1] = dp[i-1][1];
        }
        for (int i=2; i<=n; i++) {
            dp[1][i] = dp[1][i-1];
        }

        for (int i=2; i<=m;i++) {
            for(int j=2; j<=n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {
        int m = 3, n =7;
        System.out.println(uniquePaths(m, n));
        int m2 = 3, n2 = 2;
        System.out.println(uniquePaths(m2, n2));
    }
}
