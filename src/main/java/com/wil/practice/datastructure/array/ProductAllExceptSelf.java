package com.wil.practice.datastructure.array;

import java.util.Arrays;

public class ProductAllExceptSelf {


    private static int[] optimizedSpaceMemory(int[] nums) {
        int prefixProduct = 1;
        int[] res = new int[nums.length];
        res[0] = prefixProduct;
        for (int i=1; i<nums.length; i++) {
            prefixProduct *= nums[i-1];
            res[i] = prefixProduct;
        }
        int suffixProduct = 1;
        for (int j=nums.length-2; j>=0; j--) {
            suffixProduct *= nums[j+1];
            res[j] *= suffixProduct;
        }
        return res;
    }



    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] prefixes = new int[nums.length];
        int[] suffixes = new int[nums.length];
        int[] res = new int[nums.length];
        prefixes[0] = 1;
        suffixes[nums.length-1] = 1;
        for (int i=1; i<nums.length; i++) {
            prefixes[i] = prefixes[i-1] * nums[i-1];
        }
        for (int j=nums.length-2; j>=0; j--) {
            suffixes[j] = suffixes[j+1] * nums[j+1];
        }
        for (int k=0; k<nums.length; k++) {
            res[k] = prefixes[k] * suffixes[k];
        }
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(optimizedSpaceMemory(nums)));
    }

}
