package com.wil.practice.algo.slidingwindow;

public class LongestIncrementingSequence {

    public int findLongestDynamicProgramming(int[] arr) {
        int len = arr.length;
        int max =0;
        int[] dp = new int[len];
        for(int i=1; i<len; i++) {
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static int findLongestDualPointer(int[] arr) {
        int len = arr.length;
        int max =0;
        int slow =0, fast =0;

        while(fast<len) {
            if(fast>0 && arr[fast-1]<arr[fast]) {
                slow = fast-1;
                while(arr[fast-1]<arr[fast]) {
                    max = fast-slow > max? fast-slow : max;
                    fast++;
                }
                continue;
            }
            fast++;
        }
        return max+1;
    }


    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 5, 4, 3, 2, 1};
        int res =findLongestDualPointer(arr);
        System.out.println(res);
    }

}
