package com.wil.practice.datastructure.priorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxSubSequenceScore {
    private static long maxScore(int[] nums1, int[] nums2, int k) {
        if (k<1) return 0;
        long max = 0;
        int n = nums1.length;
        int[][] pairs = new int[n][2];

        for (int i=0; i<n; i++) {
            pairs[i] = new int[]{nums1[i], nums2[i]};
        }
        long sum = 0;
        int val1, val2;
        Arrays.sort(pairs, (p1, p2) -> p2[1] - p1[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<n; i++) {
            val1 = pairs[i][0];
            val2 = pairs[i][1];
            pq.add(val1);
            sum += val1;

            if (pq.size() > k) {
                sum -= pq.poll();
            }

            if (pq.size() == k) {
                max = Math.max(max, sum * val2);
            }
        }

        return max;
    }


    public static void main(String[] args) {
        int[] nums1 = new int[] {1,3,3,2};
        int[] nums2 = new int[] {2,1,3,4};
        int k = 3;
        System.out.printf("%d%n", maxScore(nums1, nums2, k));
        int[] nums3 = new int[] {4,2,3,1,1};
        int[] nums4 = new int[] {7,5,10,9,6};
        int k2 = 1;
        System.out.printf("%d%n", maxScore(nums3, nums4, k2));
    }
}
