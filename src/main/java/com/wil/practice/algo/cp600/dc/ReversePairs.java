package com.wil.practice.algo.cp600.dc;

public class ReversePairs {

    private static int reversePairs(int[] nums) {
        return reversePairsSub(nums, 0, nums.length - 1);
    }

    private static int mergeResults(int[] nums, int l, int m, int r, int res) {
        int i = l, j = m + 1, k = 0, p = m + 1;
        int[] merge = new int[r - l + 1];
        while (i <= m) {
            while (p <= r && nums[i] > 2 * nums[p]) p++;
            res += p - (m + 1);
            while (j <= r && nums[i] >= nums[j]) merge[k++] = nums[j++];
            merge[k++] = nums[i++];
        }
        while (j <= r) merge[k++] = nums[j++];
        System.arraycopy(merge, 0, nums, l, merge.length);
        return res;
    }

    private static int reversePairsSub(int[] nums, int l, int r) {
        if (l >= r) return 0;
        int m = l + (r - l)/2 ;
        int left = reversePairsSub(nums, l, m);
        int right = reversePairsSub(nums, m + 1, r);
        int res = left + right;
        return mergeResults(nums, l, m, r, res);
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 2, 3, 3, 3, 1};
        System.out.println(reversePairs(nums));
    }

}
