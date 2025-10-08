package com.wil.practice.algo.slidingwindow;

public class MaxConsecutiveOnes {

    private static int optimizedSlidingWindow(int[] nums, int k) {
        int max = k;
        int l = 0, numZeroes = 0;
        for (int r = 0; r<nums.length; r++) {
            if (nums[r] == 0) numZeroes++;
            while (numZeroes > k) {
                if (nums[l] == 0) numZeroes--;
                l++;
            }
            if (r>=k) max = Math.max(max, r-l+1);
        }
        return max;
    }


    private static int longestOnes(int[] nums, int k) {
        int max = k;
        int currentZeroes;
        for (int i=k; i<nums.length; i++) {
            currentZeroes = 0;
            for (int j=i; j>=0; j--) {
                if (nums[j] != 1) currentZeroes++;
                if (currentZeroes>k) {
                    max = Math.max(max, i-j);
                    break;
                } else {
                    max = Math.max(max, i-j+1);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;

        int[] nums2 = new int[] {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k2 = 3;

        System.out.printf("Max ones: %d%n", longestOnes(nums, k));
        System.out.printf("Max ones: %d%n", longestOnes(nums2, k2));
        System.out.printf("Max ones: %d%n", optimizedSlidingWindow(nums, k));
        System.out.printf("Max ones: %d%n", optimizedSlidingWindow(nums2, k2));
    }
}
