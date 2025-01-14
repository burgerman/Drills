package com.wil.practice.algo.cp600.dp;

public class JumpGame {

    private static boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0) continue;
            if (dp[i]) {
                for (int j = 1; j <=nums[i]; j++) {
                    if(i+j>=nums.length) break;
                    dp[i+j] = true;
                }
            }
        }
        return dp[nums.length - 1];
    }

    /**
     * The Jump Game II find minimum jump to the right end
     */
    private static int jump(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums[0]==0) return -1;
        boolean[] dp = new boolean[nums.length];
        int[] minJumps = new int[nums.length];
        dp[0] = true;
        minJumps[0] = 0;
        for (int i = 1; i < minJumps.length; i++) {
            minJumps[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0) continue;
            if (dp[i]) {
                for(int j =1; j<=nums[i]; j++) {
                    if (i+j<nums.length) {
                        dp[i+j] = true;
                        minJumps[i+j] = Math.min(minJumps[i+j], minJumps[i]+1);
                    } else {
                        break;
                    }
                }
            }
        }
        return minJumps[nums.length - 1];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(canJump(nums));
        System.out.println(jump(nums));
    }
}
