package com.wil.practice.algo;

import java.util.Arrays;

public class DualQuickSort {

    public void dualPivotQuickSort(int[]arr, int low, int high) {
        if (low>=high) {
            return;
        }
        do {
            if(low >= high) {
                return;
            }
        } while(arr[++low] >= arr[low -1]);
        Pivot pivot = partition(arr, low, high);
        int leftPivotIndex = pivot.leftPivotIndex;
        int rightPivotIndex = pivot.rightPivotIndex;
        dualPivotQuickSort(arr, low, leftPivotIndex-1);
        dualPivotQuickSort(arr, leftPivotIndex+1, rightPivotIndex-1);
        dualPivotQuickSort(arr, rightPivotIndex+1, high);
    }

    private Pivot partition(int[] arr, int low, int high) {
        if(arr[low] > arr[high]) {
            swapVal(arr, low, high);
        }
        int leftPivotIndex = low + 1;
        int rightPivotIndex = high - 1;
        int iterator = low + 1;
        while (iterator <= rightPivotIndex) {
            if(arr[iterator] > arr[low]) {
                swapVal(arr, iterator++, leftPivotIndex++);
            } else if(arr[iterator] > arr[high]) {
                swapVal(arr, iterator, rightPivotIndex--);
            } else {
                iterator ++;
            }
        }
        swapVal(arr, low, --leftPivotIndex);
        swapVal(arr, high, ++rightPivotIndex);
        return new Pivot(leftPivotIndex, rightPivotIndex);
    }

    private static final void swapVal(int[] arr, int i, int j) {
        if (arr.length > 2 & i!=j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    public static void main(String[] args) {
        DualQuickSort dqs = new DualQuickSort();
        int[] arr = {8, 24, 42, 75, 29, 77, 38, 57, 66};
        dqs.dualPivotQuickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));

    }
}

class Pivot{
    int leftPivotIndex;
    int rightPivotIndex;

    public Pivot(int leftPivotIndex, int rightPivotIndex) {
        this.leftPivotIndex = leftPivotIndex;
        this.rightPivotIndex = rightPivotIndex;
    }
}
