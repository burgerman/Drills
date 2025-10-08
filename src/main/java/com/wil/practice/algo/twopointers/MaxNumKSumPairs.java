package com.wil.practice.algo.twopointers;

import org.apache.logging.log4j.util.PropertySource;

import java.util.*;

public class MaxNumKSumPairs {

    private static int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> ledger = new HashMap<>(nums.length, 1.0f);
        int tmp;
        int max = 0;
        for (int i=0; i<nums.length; i++) {
            tmp = k - nums[i];
            if(tmp<0) continue;
            if(!ledger.containsKey(tmp)) {
                if(!ledger.containsKey(nums[i])) {
                    ledger.put(nums[i], 1);
                } else {
                    ledger.put(nums[i], ledger.get(nums[i])+1);
                }
            } else {
                if (ledger.get(tmp)>0) {
                    ledger.put(tmp, ledger.get(tmp)-1);
                    max++;
                } else {
                    if (ledger.containsKey(nums[i])) {
                        ledger.put(nums[i], ledger.get(nums[i])+1);
                    } else {
                        ledger.put(nums[i], 1);
                    }
                }
            }
        }

        return max;
    }


    private static int maxOperationsTwoPointerSolution(int[] nums, int k) {
        int max = 0;
        Arrays.sort(nums);
        int l = 0, r = nums.length-1;
        while (l<r) {
            if (nums[l]+nums[r] ==k) {
                max++;
                r--;
                l++;
            } else if (nums[l]+nums[r] <k) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4};
        int k = 2;
        int max = maxOperations(nums, k);
        int max2 = maxOperationsTwoPointerSolution(nums, k);
        System.out.printf("Max Ops: %d\n", max);
        System.out.printf("Max Ops: %d\n", max2);
    }

}
