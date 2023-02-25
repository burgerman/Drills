package com.wil.practice.algo;

public class SinglePivotQuickSort {

    public void quickSort(int[] arr, int low, int high) {
        if(low<high) {
            int i = low, j = high, pvt = arr[low];
            while(i<j) {
                while(i<j && arr[j] >= pvt) {
                    j--;
                }
                if(i<j) {
                    arr[i] = arr[j];
                    i++;
                }
                while(i<j && arr[i] < pvt) {
                    i++;
                }
                if(i<j) {
                    arr[j] = arr[i];
                    j--;
                }
            }
            arr[i] = pvt;
            quickSort(arr, low, i -1);
            quickSort(arr, i + 1, high);
        } else {
            return;
        }
    }



}
