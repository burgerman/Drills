package com.wil.practice.algo.cp600.dp;

import java.util.Arrays;

public class LongestCommonSubsequence {

    private static int[][] memo;

    private static int topDownFindLCS(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(text1, text2, 0, 0);
    }

    private static int dfs(String text1, String text2, int p1, int p2) {
        if (p1 == text1.length() || p2 == text2.length()) return 0;
        if(memo[p1][p2] != -1) return memo[p1][p2];

        int result;
        if(text1.charAt(p1) == text2.charAt(p2)) {
            result = 1 + dfs(text1, text2, p1+1, p2+1);
        } else {
            result = Math.max(dfs(text1, text2, p1+1, p2), dfs(text1, text2, p1, p2+1));
        }
        memo[p1][p2] = result;
        return result;
    }


    /**
     * Bottom-up solution
     */
    private static int findLCS(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        if (m>n) return findLCS(text2, text1);

        int[][] dp = new int[m+1][n+1];

        for (int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("Top-down result: " + topDownFindLCS(text1, text2));
        System.out.println("Bottom-up result: "+ findLCS(text1, text2));
    }

}
