package com.wil.practice.datastructure.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MaxSubarraySumCircularArray {


    private static int maxKadane(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        for (int i=1; i<nums.length; i++) {
            currentSum = Math.max(currentSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }


    private static int minKadane(int[] nums) {
        int minSum = nums[0];
        int currentSum = nums[0];
        for (int i=1; i<nums.length; i++) {
            currentSum = Math.min(currentSum + nums[i], nums[i]);
            minSum = Math.min(minSum, currentSum);
        }
        return minSum;
    }


    private static int kadaneSolution(int[] nums) {
        int maxSum = maxKadane(nums);
        int minSum = minKadane(nums);
        // IntStream.of(nums).sum();
        int total =Arrays.stream(nums).sum();
        if (maxSum < 0) return maxSum;
        return total-minSum;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] arr2 = new int[] {5, -3, 5};
        System.out.printf("Max Sum of Subarray: %d%n", kadaneSolution(arr));
        System.out.printf("Max Sum of Subarray: %d%n", kadaneSolution(arr2));

    }

}
