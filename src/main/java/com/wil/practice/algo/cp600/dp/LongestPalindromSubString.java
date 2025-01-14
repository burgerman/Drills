package com.wil.practice.algo.cp600.dp;

public class LongestPalindromSubString {




    public static void main(String[] args) {

        String s = "babad";
        int n = s.length();
        boolean dp[][] = new boolean[n][n];
        int p1=0, p2=0;
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            dp[i][i] = true; // expanding from middle
            for(int j = 0; j < i; j++) {
                if(s.charAt(j) == s.charAt(i)) {
                    if(i-j<=2 || dp[j][i-1] == true) {
                        dp[j][i] = true;
                        if(i-j+1 > maxLen) {
                            maxLen = i-j+1;
                            p1 = j;
                            p2 = i;
                        }
                    }
                } else {
                    dp[j][i]=false;
                }
            }
        }
        System.out.println(s.substring(p1, p2+1));

    }


}
