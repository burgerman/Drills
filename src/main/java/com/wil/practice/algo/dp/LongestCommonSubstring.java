package com.wil.practice.algo.dp;

public class LongestCommonSubstring {

    private static int findLongestCommon(String str1, String str2) {
        if(str1.isEmpty() || str2.isEmpty() || str1 == null || str2 == null) {
            return 0;
        }
        int m = str1.length();
        int n = str2.length();
        int[] dp = new int[n+1];
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[j] = dp[j-1] + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        String str1 = "abcef";
        String str2 = "cebcef";
        System.out.println(findLongestCommon(str1, str2));


    }



}
