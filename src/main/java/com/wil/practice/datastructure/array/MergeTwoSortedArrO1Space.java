package com.wil.practice.datastructure.array;

import java.util.Arrays;

public class MergeTwoSortedArrO1Space {


    private static void merge(int[] arr1, int[] arr2) {
        int m = arr1.length, n=arr2.length;
        for(int i=n-1; i>=0; i--){
            int j = m-2, pivot = arr1[m-1];
            while(j>=0 & arr1[j]>arr2[i]) {
                arr1[j+1] = arr1[j];
                j--;
            }
            if(pivot>arr2[i]) {
                arr1[j+1] = arr2[i];
                arr2[i] = pivot;
            }
        }
    }


    public static void main(String[] args) {
         int arr1[] = new int[] { 1, 5, 9, 10, 15, 20 };
         int arr2[] = new int[] { 2, 3, 8, 13 };
         merge(arr1, arr2);
         System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }


}
