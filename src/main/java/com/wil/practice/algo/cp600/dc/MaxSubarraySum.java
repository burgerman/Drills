package com.wil.practice.algo.cp600.dc;

public class MaxSubarraySum {
    /**
     * Find maximum sum of subarray that crosses the midpoint.
     * This handles the case where the optimal subarray spans both halves.
     * KEY INSIGHT: We're looking for subarrays that MUST include elements from both sides.
     * The crossing subarray must be contiguous and include the midpoint boundary.
     */
    private static int crossSubArrSum(int left, int mid, int right, int[] nums) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int tmpSum = 0;
        // start from midpoint left
        for (int i = mid; i >= left; i--) {
            tmpSum += nums[i];
            leftSum = Math.max(leftSum, tmpSum);
        }
        tmpSum = 0;
        // start from midpoint right
        for (int i=mid+1; i <=right; i++) {
            tmpSum += nums[i];
            rightSum = Math.max(rightSum, tmpSum);
        }
        return leftSum + rightSum;
    }


    private static int maxSubArrSum(int left, int right, int[] nums) {
        if(left == right) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        int leftSubMax = maxSubArrSum(left, mid, nums);
        int rightSubMax = maxSubArrSum(mid + 1, right, nums);
        int crossSubMax = crossSubArrSum(left, mid, right, nums);
        return Math.max(Math.max(leftSubMax, rightSubMax), crossSubMax);
    }

    private static int maxSubarraySumDivideConquer(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty");
        }
        return maxSubArrSum(0, nums.length - 1, nums);

    }


    private static int kadaneSolution(int[] nums) {
        int maxSum = nums[0];
        int currentMaxSum = nums[0];
        for(int i =1; i<nums.length; i++) {
            currentMaxSum = Math.max(currentMaxSum+nums[i], nums[i]);
            maxSum = Math.max(currentMaxSum, maxSum);
        }
        return maxSum;
    }


    public static void main(String[] args) {
        int[] arr = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSubSum = maxSubarraySumDivideConquer(arr);
        System.out.printf("Max Sum of Subarray: %d%n", maxSubSum);

        System.out.printf("Max Sum of Subarray Using Kadane's algo: %d%n", kadaneSolution(arr));
    }

}
