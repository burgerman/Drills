package com.wil.practice.algo.twopass;

public class LongestOnesSubArray {

    /***
     * Two-pass technique
     * @param nums
     * @return
     */
    private static int longestSubarray(int[] nums) {
        if (nums.length<2) return 0;
        int[] res = new int[nums.length];
        res[0] = 0;
        int max = nums[0]>0? 1:0;
        int longest = max;
        for (int i=1; i<nums.length; i++) {
            res[i] = max;
            max = nums[i]>0? max+1:0;
        }
        longest = Math.max(longest, res[nums.length-1]);
        max = nums[nums.length-1]>0? 1:0;
        for (int i=nums.length-2; i>=0; i--) {
            res[i] = res[i] + max;
            max = nums[i]>0? max+1:0;
            longest = Math.max(longest, res[i]);
        }
        return longest;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {1,1,0,1};
        int[] nums2 = new int[] {0,1,1,1,0,1,1,0,1};
        int[] nums3 = new int[] {1, 0};
        System.out.printf("Longest 1s subarray after one deletion: %d%n", longestSubarray(nums));
        System.out.printf("Longest 1s subarray after one deletion: %d%n", longestSubarray(nums2));
        System.out.printf("Longest 1s subarray after one deletion: %d%n", longestSubarray(nums3));
    }
}
