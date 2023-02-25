package com.wil.practice.interview;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * The given arr has been sorted in ascending order
 * Return index of the smallest value >= target in arr, if not found return -1;
 */
public class BinarySearch {


    public static void main(String[] args) {

        int[] arr = new int[] {1, 3, 5, 8, 10, 11};
        int target = 8;
        System.out.println(binarySearch(arr, 0, arr.length-1, target));
    }


    public static int binarySearch(int[] arr, int start, int end, int target) {
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


    int simpleBinarySearch(Integer arr[], int l, int r, int x)
    {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr[mid] == x)
                return mid;

                // If element is smaller than mid, then
                // it can only be present in left subarray
            else if (arr[mid] > x)
                return simpleBinarySearch(arr, l, mid - 1, x);

                // Else the element can only be present
                // in right subarray
            else return simpleBinarySearch(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }


    public static int binaryRetrieve(Integer[] arr, Integer target) {
        int l = 0, r = arr.length-1;
        while (l<=r) {
            int mid = l+ (r-l)>>1;
            if(arr[mid].compareTo(target) == 0) {
                return mid;
            } else if(arr[mid].compareTo(target) >0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}
