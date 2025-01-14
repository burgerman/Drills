package com.wil.practice.algo.cp600.strin;

import java.util.Arrays;

public class BoyerMooreSearch {
    private static int[] buildBadCharTable(String pattern) {
        int[] table = new int[256];
        int length = pattern.length();

        // Initialize all shift values to the pattern length
        Arrays.fill(table, length);
        // Fill the table based on the pattern
        for (int i = 0; i < length - 1; i++) {
            table[pattern.charAt(i)] = length - 1 - i;
        }
        return table;
    }

    private static int search(String text, String pattern, int[] table) {
        int skip;
        for(int i = 0; i <= text.length()-pattern.length(); i+=skip) {
            skip = 0;
            for(int j = pattern.length()-1; j>=0; j--) {
                if(pattern.charAt(j) != text.charAt(i+j)) {
                    skip = table[text.charAt(i+j)];
                    break;
                }
            }
            if(skip == 0) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "testpattern";
        String pattern = "tpa";
        int[] tbl = buildBadCharTable(pattern);
        int startPos = search(s, pattern, tbl);
        System.out.println(s.substring(startPos, startPos+pattern.length()));
    }
}
