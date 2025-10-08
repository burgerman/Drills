package com.wil.practice.algo.twopointers;

public class FindDupNum {

    private static int slowFastPointerFindDup(int[] nums) {
        int slow = nums[0], fast = nums[0];
        // Floyd's Cycle Detection
        for (;;) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }
        //Reset one pointer to the start
        slow = nums[0];
        // Find Cycle Entrance
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        // meet at the cycle entrance (duplicate number found)
        return slow;
    }

}
