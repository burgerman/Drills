package com.wil.practice.algo.cp600.dc;

import java.util.ArrayList;
import java.util.List;

public class LIS {

    /**
     * using binary search approach
     * time complexity O(n lgn)
     */
    private static int lengthOfLIS(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for(int num : nums) {
            int pos = binarySearch(ans, num);

            if(pos == ans.size()) {
                ans.add(num);
            }
            else {
                ans.set(pos, num);
            }
        }
        return ans.size();
    }

    private static int binarySearch(List<Integer> ans, int target) {
        int low = 0;
        int high = ans.size() - 1;

        while(low <= high) {
            int mid = low + (high-low)/2;

            if(ans.get(mid) < target) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }
}
