package com.wil.practice.algo.slidwindw;

import java.util.HashSet;

public class RemoveDup {

    public static int getNumOfDedup(int[] nums) {
        int len = nums.length;
        if(len<2) {
            return 1;
        }
        HashSet<Integer> set = new HashSet(len);
        int j =1;
        int lastNum = nums[0];
        set.add(lastNum);
        for(int i =1; i<len; i++) {
            if(nums[i] != lastNum && !set.contains(nums[i])) {
                lastNum = nums[i];
                set.add(lastNum);
                j++;
            }
        }
        nums = set.stream().mapToInt(Integer::intValue).toArray();
        return j;
    }

    public static int getNumOfDedup2(int[] nums) {
        int len = nums.length;
        if(len<2) {
            return 1;
        }
        boolean[] bitVector = new boolean[30000];
        int j =1;
        int lastNum = nums[0];
        bitVector[lastNum] = true;
        for(int i =1; i<len; i++) {
            if(nums[i] != lastNum && !bitVector[nums[i]]) {
                lastNum = nums[i];
                bitVector[lastNum] = true;
                j++;
            }
        }
        return j;
    }


    public static int genericDualPointer(int[] nums) {
        return dedupOriginArr(nums, 2);
    }

    private static int dedupOriginArr(int[] nums, int k) {
        int i=k;
        for (int j=k; j<nums.length; j++) {
            if (nums[i-k] != nums[j]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 2, 2, 2, 2, 3, 4, 4, 5};
        System.out.println(getNumOfDedup(arr));
        System.out.println(getNumOfDedup2(arr));
        int[] nums = new int[]{1,1,1,1,1,1,2,2,2,2,2,2,3};
        System.out.println(genericDualPointer(nums));
    }

}
