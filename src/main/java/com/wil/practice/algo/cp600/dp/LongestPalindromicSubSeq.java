package com.wil.practice.algo.cp600.dp;

public class LongestPalindromicSubSeq {


    private static int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        return recursion(s, 0, s.length() - 1, dp);
    }

    private static int recursion(String s, int i, int j, int[][] dp) {
        if (i == j) return 1;
        if (i > j) return 0;

        if (dp[i][j] != 0) return dp[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = recursion(s, i + 1, j - 1, dp) + 2;
        } else {
            dp[i][j] = Math.max(recursion(s, i + 1, j, dp), recursion(s, i, j - 1, dp));
        }

        return dp[i][j];
    }

    public static void main(String[] args) {
        String s = "strabetubsa";
        System.out.println(longestPalindromeSubseq(s));
    }



}
