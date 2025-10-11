package com.wil.practice.algo.twopointers;

import java.util.Arrays;

public class TwoPointersFindClosest {


    private static int[] findClosestPair(int[] arr, int base) {
        int diff = Integer.MAX_VALUE;
        int left = 0;
        int right = arr.length-1;
        int indL = 0;
        int indR = 0;
        int distance;
        while(left<right) {
            distance = Math.abs(arr[left]+arr[right]-base);
            if(Math.abs(arr[left]+arr[right]-base)<diff) {
                indL=left;
                indR=right;
                diff = distance;
            }

            if(arr[left]+arr[right] < base) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{arr[indL], arr[indR]};
    }


    public static void main(String[] args) {

        int[] arr = new int[]{10, 22, 28, 29, 30, 40};
        int base = 54;
        System.out.println(Arrays.toString(findClosestPair(arr, base)));
    }

}
