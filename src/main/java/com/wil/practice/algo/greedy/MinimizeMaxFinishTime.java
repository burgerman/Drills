package com.wil.practice.algo.greedy;

import java.util.*;

public class MinimizeMaxFinishTime {

    /**
     * A helper class to store information about each group of 4 elements from list2.
     */
    private static class Group {
        long sum;
        int maxElement;

        public Group(long sum, int maxElement) {
            this.sum = sum;
            this.maxElement = maxElement;
        }
    }

    /**
     * Finds an assignment to minimize the maximum finish time and returns the minimum
     * finish time from that optimal assignment.
     *
     * @param list1 A list of n base times.
     * @param list2 A list of 4*n elements to be grouped.
     * @return The minimum finish time from the optimal assignment.
     */
    private static int solve(List<Integer> list1, List<Integer> list2) {
        int n = list1.size();
        Map<Integer, Group> map = new HashMap<>();
        // 1. Sort both lists
        Collections.sort(list1);
        Collections.sort(list2);

        // 2. Form n groups using the "3 small, 1 large" heuristic
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // Use long for sum to prevent overflow
            long currentSum = (long) list2.get(3 * i) +
                    list2.get(3 * i + 1) +
                    list2.get(3 * i + 2) +
                    list2.get(4 * n - 1 - i);

            int currentMax = list2.get(4 * n - 1 - i);
            groups.add(new Group(currentSum, currentMax));
        }

        // 3. Sort groups by their sum in ascending order
        groups.sort(Comparator.comparingLong(g -> g.sum));

        // 4. Assign groups to base times and calculate finish times
        long minFinishTime = Long.MAX_VALUE;
        long baseTime, finishTime;
        for (int i = n-1; i >=0; i--) {
            baseTime = list1.get(i);
            // Smallest base time gets the group with the largest sum
            Group groupToAssign = groups.get(n - 1 - i);
            if (groupToAssign.maxElement <= baseTime) {
                finishTime = baseTime + groupToAssign.sum;
                System.out.println("Finish Time:" + finishTime);
                minFinishTime = Math.min(minFinishTime, finishTime);
            } else {
                break;
            }
        }

        // 6. Return the minimum finish time from the calculated set
        return (int) minFinishTime;
    }


    public static void main(String[] args) {
        Integer[] arr1 = {8, 10};
        List<Integer> list1 = Arrays.asList(arr1);
        Integer[] arr2 = {2, 2, 3, 1, 8, 7, 4, 5};
        List<Integer> list2 = Arrays.asList(arr2);
        int result = solve(list1, list2);
        System.out.println(result);
    }

}
