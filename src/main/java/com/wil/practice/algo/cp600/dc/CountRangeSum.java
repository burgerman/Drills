package com.wil.practice.algo.cp600.dc;

public class CountRangeSum {


    private static int mergeResults(int[]nums, int l, int r, int lower, int upper) {
        int m = l+(r-l)/2;
        int count =0;
        int i=l;
        int sum=0;
        while(i<=r) {
            sum+=nums[i];
            if((sum<lower || sum>upper)&&i>m) {
                if(sum-nums[i]>=lower && sum-nums[i]<=upper) {
                    count++;
                }
            }
            i++;
        }
        if(lower<=sum&&sum<=upper) count++;
        return count;
    }

    private static int mergeSortFind(int[]nums, int l, int r, int lower, int upper) {
        if(l==r) {
            if(lower<=nums[l] && nums[l]<=upper) return 1;
            else return 0;
        }
        int m = l+(r-l)/2;
        int leftSum = mergeSortFind(nums,l,m,lower,upper);
        int rightSum = mergeSortFind(nums,m+1,r,lower,upper);
        int crossSum = mergeResults(nums,l,r,lower,upper);
        int sum = leftSum+rightSum+crossSum;
        return sum;
    }

    private static int countRangeSum(int[] nums, int lower, int upper) {
       return mergeSortFind(nums,0,nums.length-1, lower,upper);
    }

    public static void main(String[] args) {

        System.out.println(countRangeSum(new int[]{0,-3,-3,1,1,2},3,5));
    }
}
