package com.wil.practice.datastructure.array;

public class IncreasingTriplet {
    private static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int m=0; m<nums.length; m++) {
            if (nums[m] <= first) first = nums[m];
            else if (nums[m] <= second) second = nums[m];
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[] {2,4,-2,-3}));
        System.out.println(increasingTriplet(new int[] {20,100,10,12,5,13}));
    }
}
