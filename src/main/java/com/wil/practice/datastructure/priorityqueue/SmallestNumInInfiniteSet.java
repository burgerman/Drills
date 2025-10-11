package com.wil.practice.datastructure.priorityqueue;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SmallestNumInInfiniteSet {
    private static class SmallestInfiniteSet {
        private PriorityQueue<Integer> minHeap;
        private Set<Integer> added;
        private int current;

        public SmallestInfiniteSet() {
            minHeap = new PriorityQueue<>();
            added = new HashSet<>();
            current = 1;
        }

        public int popSmallest() {
            if (!minHeap.isEmpty()) {
                int smallest = minHeap.poll();
                added.remove(smallest);
                return smallest;
            }
            return current++;
        }

        public void addBack(int num) {
            if (num < current && !added.contains(num)) {
                added.add(num);
                minHeap.offer(num);
            }
        }
    }

    public static void main(String[] args) {
        SmallestInfiniteSet set = new SmallestInfiniteSet();

        System.out.println(set.popSmallest()); // 1
        System.out.println(set.popSmallest()); // 2
        System.out.println(set.popSmallest()); // 3

        set.addBack(2);
        System.out.println(set.popSmallest()); // 2
        System.out.println(set.popSmallest()); // 4

        set.addBack(1);
        System.out.println(set.popSmallest()); // 1
        System.out.println(set.popSmallest()); // 5
    }
}
