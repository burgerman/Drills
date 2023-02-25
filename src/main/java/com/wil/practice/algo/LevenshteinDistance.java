package com.wil.practice.algo;

import javax.crypto.spec.PSource;
import java.util.Scanner;

public class LevenshteinDistance {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CharSequence source = sc.next(), target = sc.next();
        int i = source.length()>0? source.length()-1:0,
                j = target.length()>0?target.length()-1:0;

        int min1 = topDown(source, target, i, j);
        System.out.println(min1);

        int min2 = bottomUp(source, target);
        System.out.println(min2);
    }


    //Tabulation
    private static int topDown(CharSequence source, CharSequence target, int i, int j) {
        // if source misses the rest of chars of target
        if(i==-1) {
            return j+1;
        }

        // if target misses the rest of chars of source
        if(j==-1) {
            return i+1;
        }

        // when two chars are same
        if (source.charAt(i) == target.charAt(j)) {
            //skip
            return topDown(source, target, i-1, j-1);
        } else {
            // find the minimum of levenshtein distance among (insert, delete, replace)
            return min(topDown(source, target, i, j-1)+1, topDown(source, target, i-1, j)+1, topDown(source,target,i-1, j-1)+1);
        }

    }

    static int min(int a, int b, int c){
        return Math.min(a, Math.min(b,c));
    }


    //Memoization
    private static int bottomUp(CharSequence source, CharSequence target) {
        int m = source.length(), n = target.length();
        int[][] dp = new int[m+1][n+1];
        //Base case
        //when target is empty
        for(int i=1; i<m+1; i++) {
            dp[i][0]=i;
        }
        //when source is empty
        for(int j=1; j<n+1; j++) {
            dp[0][j]=j;
        }
        for(int k=1; k<m+1; k++) {
            for(int l=1; l<n+1; l++) {
                //when two chars are same
                if(source.charAt(k-1) == target.charAt(l-1)) {
                    dp[k][l] = dp[k-1][l-1];
                } else {
                    // the minimum of levenshtein distance among (insert, delete, replace)
                    dp[k][l] = min(dp[k][l-1], dp[k-1][l],dp[k-1][l-1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
