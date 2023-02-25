package com.wil.practice.algo.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DPcanSum {

    private static int arrSum = 0;

    public static void main(String[] args) {
        Map<Integer, Boolean> memo = new HashMap<>();
        int[] arr = {5, 3, 4, 2, 1, 7};
        int targetSum = 50;
        arrSum = sumArr(arr, arr.length-1);
        System.out.println(canSum(arr, targetSum, memo));
    }

    public static int sumArr(int[] arr, int end) {
        if(end==0) {
            return arr[end];
        }
        return arr[end] + sumArr(arr, end-1);
    }

    public static boolean canSum(int[] arr, int targetSum, Map<Integer, Boolean> memo) {
        if(memo.keySet().contains(targetSum)) {
            return memo.get(targetSum);
        }
        if(targetSum == 0) {
            return true;
        }
        if(targetSum<0 || targetSum> arrSum) {
            return false;
        }
        for (int num: arr) {
            int remainder = targetSum-num;
            if(canSum(arr, remainder, memo)) {
                memo.put(targetSum, Boolean.TRUE);
                return true;
            }
        }
        memo.put(targetSum, Boolean.FALSE);
        return false;
    }


}
