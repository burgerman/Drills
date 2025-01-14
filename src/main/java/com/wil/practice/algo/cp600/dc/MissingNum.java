package com.wil.practice.algo.cp600.dc;

import java.util.LinkedList;
import java.util.List;

public class MissingNum {

    private static Integer convertToBinary(Integer decimalNum) {
        int i = 1;
        int binaryNumber = 0;
        while (decimalNum != 0) {
            binaryNumber += (decimalNum % 2) * i;
            decimalNum /= 2;
            i *= 10;
        }
        return binaryNumber;
    }

    // Using XOR to find out missing binary
    private static Integer findMissingBinary(List<String> binaryStrings) {
        int size = binaryStrings.size()+1;
        int len = (int)(Math.log(size)/Math.log(2));
        Integer missing = 0;
        for (int i = 0; i < binaryStrings.size(); i++) {
            Integer b = Integer.parseInt(binaryStrings.get(i), 2);
            missing ^= b;
        }
        return missing;
    }


    public static int findMissing(int[] arr, int n) {
        int left = 0;
        int right = arr.length - 1;
        int start = -n / 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int expected = mid + start;

            // If the current value doesn't match the expected, search left side
            if (arr[mid] != expected) {
                right = mid - 1;
            } else {
                // Otherwise, search the right side
                left = mid + 1;
            }
        }

        // The missing number is at the position `left` in terms of expected sequence
        return left + start;
    }


    public static int findMissingPositive(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the value at mid is as expected
            if (arr[mid] != mid) {
                // If it's not, missing number is on the left side
                right = mid - 1;
            } else {
                // If it matches, move to the right side
                left = mid + 1;
            }
        }

        // The missing number is at position `left` in terms of the 1-indexed sequence
        return left;
    }



    public static void main(String[] args) {
        List<String> binaryStrings = new LinkedList<>();
        binaryStrings.add("01");
        binaryStrings.add("10");
        binaryStrings.add("11");
//        System.out.println("Missing Num: "+Integer.toBinaryString(findMissingBinary(binaryStrings)));

        int[] arr = {-3, -1, 0, 1, 2, 3};
        int n = arr.length + 1;
        System.out.println("Missing integer: " + findMissing(arr, n));

        int[] arr2 = {0, 1, 3, 4, 5};
        System.out.println("Missing integer: " + findMissingPositive(arr2));
    }
}
