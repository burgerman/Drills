package com.wil.practice.interview;

public class CountGoodTriplets {
    /**
     * 0 <= i < j < k < arr.length
     * Conditions for good triplets:
     * |arr[i] - arr[j]| <= a
     * |arr[j] - arr[k]| <= b
     * |arr[i] - arr[k]| <= c
     * @param arr
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (Math.abs(arr[i] - arr[j]) > a) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    if (Math.abs(arr[j] - arr[k]) > b) {
                        continue;
                    } else if (Math.abs(arr[i] - arr[k]) <= c) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
