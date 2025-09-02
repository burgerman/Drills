package com.wil.practice.algo.cp600.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartitionEqualSubset {

    private static Set<List<Integer>> findSubSets(int[] nums) {
        int total = 0;
        for(int num : nums) {
            total += num;
        }
        if (total % 2 != 0) return new HashSet<>();
        int subTotal = total >> 1;

        boolean[] selected = new boolean[nums.length];

        Set<List<Integer>> partitions = new HashSet<>();

        if(isSplittable(nums, 0, subTotal, selected)) {
            List<Integer> subSet1 = new ArrayList<>();
            List<Integer> subSet2 = new ArrayList<>();
            for (int i =0; i<selected.length; i++){
                if (selected[i]) {
                    subSet1.add(nums[i]);
                } else {
                    subSet2.add(nums[i]);
                }
            }
            partitions.add(subSet1);
            partitions.add(subSet2);
        }
        return partitions;
    }


    private static boolean isSplittable(int[] nums, int index, int target, boolean[] selected) {
        if (target == 0) return true;
        if (index >= nums.length || target < 0) return false;

        selected[index] = true;

        if (isSplittable(nums, index+1, target-nums[index], selected)){
            return true;
        }

        selected[index] = false;
        if(isSplittable(nums, index+1, target, selected)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        int[] testNums = {1, 5, 11, 5};
        int[] testNums2 = {1, 2, 3, 5};
        int[] testNums3 = {2, 2, 1, 1};
        System.out.println(findSubSets(testNums));
        System.out.println(findSubSets(testNums2));
        System.out.println(findSubSets(testNums3));
    }

}
