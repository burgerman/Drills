package com.wil.practice.algo;

import java.util.ArrayDeque;
import java.util.Arrays;

public class InsertionSort {

    public void insertionSort(int[] arr) {
        for (int i = 1; i<arr.length; i++) {
            int toBeInserted = arr[i];
            int j = i;
            // Move those elements greater than the to-be-inserted to its right
            while(j>0 && toBeInserted < arr[j-1]) {
                arr[j] = arr[j-1];
                j--;
            }
            // insert element into the current pos of j
            if(i!=j) {
                arr[j] = toBeInserted;
            }
        }
    }

    void rangeCheck(int[] arr, int l, int r){
        if (l > r) {
            throw new IllegalArgumentException(
                    "fromIndex(" + l + ") > toIndex(" + r + ")");
        }
        if (l < 0) {
            throw new ArrayIndexOutOfBoundsException(l);
        }
        if (r > arr.length) {
            throw new ArrayIndexOutOfBoundsException(r);
        }
    }

    int binarySearch(Integer arr[], int start, int end, int target)
    {
        int low = start;
        int high = end - 1;

        while (low <= high) {
            int mid = low +(high-low) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        // key not found.
        return -(low + 1);
    }


    int binarySearch2(Integer a[], int item, int low, int high)
    {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (item == a[mid]) {
                return mid + 1;
            } else if (item > a[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public void binaryInsertionSort(Integer [] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i -1;
            int current = arr[i];
//            int loc = binarySearch2(arr, current, 0, j);
//            int loc = Math.abs(Arrays.binarySearch(arr, 0, i, current)+1);
            int loc = Math.abs(binarySearch(arr, 0, i, current)+1);
            while( j >= loc) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = current;
        }
    }


    public static void main(String[] args) {
        InsertionSort is = new InsertionSort();
        Integer [] arr = { 37, 23, 0, 17, 12, 72, 31, 46, 100, 88, 54 };
        System.out.println(Arrays.binarySearch(arr, 0, arr.length-1, 17));
        System.out.println(is.binarySearch(arr, 0, arr.length-1, 17));
        is.binaryInsertionSort(arr);
        System.out.println(Arrays.deepToString(arr));
        Arrays.sort(arr);
    }

}
