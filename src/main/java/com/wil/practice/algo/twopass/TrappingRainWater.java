package com.wil.practice.algo.twopass;

public class TrappingRainWater {

    private static int twoPassSolution(int[] nums) {
        int sum = 0;
        int[] res = new int[nums.length];
        res[0] = 0;
        int max = nums[0];
        for (int i=1; i<nums.length; i++) {
            res[i] = max;
            max = Math.max(max, nums[i]);
        }
        res[nums.length-1] = 0;
        max = nums[nums.length-1];

        for (int i = nums.length-2; i>=0; i--) {
            res[i] = Math.min(res[i], max) - nums[i];
            if (res[i]>0) sum += res[i];
            max = Math.max(max, nums[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] heights = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] heights2 = new int[] {4,2,0,3,2,5};
        System.out.printf("Max Water Trapped: %d%n", twoPassSolution(heights));
        System.out.printf("Max Water Trapped: %d%n", twoPassSolution(heights2));
    }
}
