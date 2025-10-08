package com.wil.practice.algo.twopass;

public class FindPivotIndex {

    private static int twoPassSolution(int[] nums) {
        int[] res = new int[nums.length];
        res[nums.length-1] = 0;
        for (int i=nums.length-2; i>=0; i--) {
            res[i] = res[i+1]+nums[i+1];
        }
        if (res[0] == 0) return 0;
        res[0] = 0;
        int leftSum;
        for (int i=1; i<nums.length; i++) {
            leftSum = res[i-1]+nums[i-1];
            if (res[i] == leftSum) return i;
            else res[i] = leftSum;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,7,3,6,5,6};
        int[] nums2 = new int[] {-1,-1,-1,-1,-1,0};
        System.out.printf("Pivot Index: %d%n", twoPassSolution(nums));
        System.out.printf("Pivot Index: %d%n", twoPassSolution(nums2));
    }



}
