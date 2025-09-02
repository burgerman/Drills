package com.wil.practice.algo.cp600.pointers;

import java.util.*;

public class PalindromeIndex {

    public static int palindromeIndex(String s) {
        if (s.length()<2) return -1;
        int left = 0;
        int right = s.length() - 1;

        // Find the first mismatch using two pointers
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Found a mismatch - try removing either left or right character
                // Option 1: Remove left character (skip left, check remaining)
                if (isPalindromeInRange(s, left + 1, right)) return left;
                // Option 2: Remove right character (skip right, check remaining)
                else if (isPalindromeInRange(s, left, right - 1)) return right;
                else return -1;
            }
            left++;
            right--;
        }

        // String is already a palindrome
        return -1;
    }

    // Helper method to check if substring from start to end is palindrome
    private static boolean isPalindromeInRange(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // Test the solution with the provided examples
    public static void main(String[] args) {
        // Test cases from the problem
        System.out.println(palindromeIndex("aaab")); // Expected: 3
        System.out.println(palindromeIndex("baa"));  // Expected: 0
        System.out.println(palindromeIndex("aaa"));  // Expected: -1

        // Additional test cases
        System.out.println(palindromeIndex("bcbc")); // Expected: 0 or 3
        System.out.println(palindromeIndex("raceacar")); // Expected: 3 (remove 'a')

        // Edge cases
        System.out.println(palindromeIndex("ab")); // Expected: 0 or 1
        System.out.println(palindromeIndex("a")); // Expected: -1
    }
}