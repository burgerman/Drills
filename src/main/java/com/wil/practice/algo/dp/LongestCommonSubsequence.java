package com.wil.practice.algo.dp;

public class LongestCommonSubsequence {

    private static int findLongestCommon(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        if (m<1 || n<1) return 0;
        int[][] dp = new int[m+1][n+1];
        for (int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {
        String str1 = "abcef";
        String str2 = "cebcef";
        System.out.println(findLongestCommon(str1, str2));

        String str3 = "abc";
        String str4 = "def";
        System.out.println(findLongestCommon(str3, str4));
    }



}
