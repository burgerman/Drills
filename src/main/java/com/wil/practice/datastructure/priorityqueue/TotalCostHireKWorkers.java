package com.wil.practice.datastructure.priorityqueue;

import java.util.PriorityQueue;

public class TotalCostHireKWorkers {

    private static long totalCost(int[] costs, int k, int candidates) {
        long totalCost = 0;
        int l = 0, r = costs.length-1;
        int minLeft, minRight;
        PriorityQueue<Integer> leftPQ = new PriorityQueue<>();
        PriorityQueue<Integer> rightPQ = new PriorityQueue<>();
        for (int i = 0; i<k; i++) {
            minLeft  = Integer.MAX_VALUE;
            minRight = Integer.MAX_VALUE;
            while (l<=r && leftPQ.size() < candidates){
                leftPQ.add(costs[l]);
                l++;
            }

            while (l<=r && rightPQ.size() < candidates) {
                rightPQ.add(costs[r]);
                r--;
            }
            if (!leftPQ.isEmpty()) minLeft = leftPQ.peek();
            if (!rightPQ.isEmpty()) minRight = rightPQ.peek();

            if (minLeft <= minRight) {
                leftPQ.poll();
                totalCost += minLeft;
            }
            else {
                rightPQ.poll();
                totalCost += minRight;
            }

        }
        return totalCost;
    }



    public static void main(String[] args) {
        int[] costs = new int[] {17,12,10,2,7,2,11,20,8};
        int[] costs2 = new int[] {31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58};
        int k = 3, k2 = 11;
        int candidates = 4, candidates2 = 2;
        System.out.printf("%d%n", totalCost(costs, k, candidates));
        System.out.printf("%d%n", totalCost(costs2, k2, candidates2));
    }
}
