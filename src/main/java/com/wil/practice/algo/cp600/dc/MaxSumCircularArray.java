package com.wil.practice.algo.cp600.dc;

public class MaxSumCircularArray {

    public static int maxSubarraySumCircular(int[] nums) {
        int maxKadane = kadane(nums);// max sub arr sum using Kadane's algorithm
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        for (int i = 0; i < nums.length; i++) nums[i] = -nums[i]; // reverse the array
        int minKadane = kadane(nums);
        if (minKadane == -totalSum) return maxKadane; // all elements are negative, return the maxKadane
        return Math.max(maxKadane, totalSum + minKadane);
    }

    private static int kadane(int[] nums) {
        if(nums.length==1) return nums[0];
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {5,4,-1,7,8};

    }

}
