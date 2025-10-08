package com.wil.practice.algo.slidingwindow;

import java.util.ArrayDeque;

public class MaxNumVowelsInSubString {

    private static char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
    private static int maxVowels(String s, int k) {
        int max =0;
        char obsolete, newAdd;
        ArrayDeque<Character> substr = new ArrayDeque<>(k);
        for (int i = 0; i < k; i++) {
            newAdd = s.charAt(i);
            substr.offer(newAdd);
            for (char vowel : vowels) {
                if (vowel == newAdd) max++;
            }
        }
        int currentNum = max;
        for (int i = k; i < s.length(); i++) {
            newAdd = s.charAt(i);
            obsolete = substr.poll();
            substr.offer((newAdd));
            for (char vowel : vowels) {
                if(vowel == newAdd) currentNum++;
                if(vowel == obsolete) currentNum--;
            }
            max = Math.max(currentNum, max);
        }
        return max;

    }


    public static void main(String[] args) {
        String s = "abciiidef";
        String s2 = "aeiou";
        String s3 = "leetcode";
        int k = 3;
        int k2 = 2;
        int k3 = 3;
        System.out.printf("max num: %d%n", maxVowels(s, k));
        System.out.printf("max num: %d%n", maxVowels(s2, k2));
        System.out.printf("max num: %d%n", maxVowels(s3, k3));
    }

}
