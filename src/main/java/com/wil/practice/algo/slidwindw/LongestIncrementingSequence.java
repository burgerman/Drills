package com.wil.practice.algo.slidwindw;

public class LongestIncrementingSequence {

    public int findLongestDynamicProgramming(int[] arr) {
        int len = arr.length;
        int maxCommon =0;
        int[] dp = new int[len];
        for(int i=1; i<len; i++) {
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxCommon = Math.max(maxCommon, dp[i]);
        }
        return maxCommon;
    }

    public static int findLongestDualPointer(int[] arr) {
        int len = arr.length;
        int maxCommon =0;
        int slow =0;
        int fast =0;

        while(fast<len) {
            if(fast>0 && arr[fast-1]<arr[fast]) {
                slow = fast-1;
                while(arr[fast-1]<arr[fast]) {
                    if (maxCommon< fast-slow) {
                        maxCommon = fast-slow;
                    }
                    fast++;
                }
                continue;
            }
            fast++;
        }
        return maxCommon+1;
    }


    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 5, 4, 3, 2, 1};
        int res =findLongestDualPointer(arr);
        System.out.println(res);
    }

}
