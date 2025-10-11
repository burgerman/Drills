package com.wil.practice.datastructure.priorityqueue;

import java.util.PriorityQueue;

public class KthLargestElement {

    private static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int i : nums) {
            pq.add(i);
        }
        int res = 0;
        for (int i = 1; i<=k; i++) {
            if (!pq.isEmpty()) res = pq.poll();
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {3,2,1,5,6,4};
        int k = 2;
        int[] nums2 = new int[] {3,2,3,1,2,4,5,5,6};
        int k2 = 4;
        System.out.printf("%d%n", findKthLargest(nums, k));
        System.out.printf("%d%n", findKthLargest(nums2, k2));
    }


}
