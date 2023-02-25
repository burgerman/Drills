package com.wil.practice.algo;

import java.util.Arrays;

public class QuickSortWithSentinel {

    public void sortWithSentinel (int[] arr, int left, int right) {
        do{
            if(left >= right) {
                return;
            }
        } while (arr[++left] >= arr[left -1]);
        /**
         * Every element from adjoining part plays the role
         * of sentinel. Therefore, this allows us to avoid the
         * left range check on each iteration. Moreover, we use
         * the more optimized algorithms, so called 'pair insertion sort',
         * which is faster (in the context of QuickSort)
         * than traditional implementation of insertion sort
         */
        for (int iterator = left; ++left <= right; iterator = ++left) {
            int a1 = arr[iterator], a2 = arr[left];
            if(a1<a2) {
                a2 = a1; a1 = arr[left];
            }
            while (a1 < arr[--iterator]) {
                arr[iterator + 2] = arr[iterator];
            }
            arr[++iterator + 1] = a1;

            while (a2 < arr[--iterator]) {
                arr[iterator + 1] = arr[iterator];
            }
            arr[iterator + 1] = a2;
        }
        int last = arr[right];
        while (last<arr[--right]) {
            arr[right + 1] = arr[right];
        }
        arr[right + 1] = last;
    }


    public static void main(String[] args) {
        QuickSortWithSentinel qsws = new QuickSortWithSentinel();
        int[] arr = {8, 24, 42, 75, 29, 77, 38, 57, 66};
        qsws.sortWithSentinel(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
