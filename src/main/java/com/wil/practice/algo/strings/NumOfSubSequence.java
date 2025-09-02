package com.wil.practice.algo.strings;

import java.util.*;

public class NumOfSubSequence {

    private static int getNumOfSubSequence(String s, String[] words) {
        Deque<Character> stackS = new ArrayDeque<>();
        Deque<Character> tmpS;
        int result = 0;
        char[] wordArr;
        int freq;
        Map<Character, Integer> dictWord;
        Map<Character, Integer> dictS = new HashMap<>(32, 1.0f);
        for (char c : s.toCharArray()) {
            freq = dictS.containsKey(c)? dictS.get(c) + 1 : 1;
            dictS.put(c, freq);
            stackS.push(c);
        }

        for (String word : words) {
            if (word.length()>s.length()) continue;
            if (word.length()==s.length() && word.equals(s)) {
                result++;
                continue;
            }
            tmpS = new ArrayDeque<>(stackS);
            dictWord = new HashMap<>(word.length(),1.0f);
            wordArr = word.toCharArray();

            for (int i=wordArr.length-1; i>=0; i--) {
                if(!dictS.containsKey(wordArr[i])) break;
                if (dictWord.containsKey(wordArr[i]) && dictWord.get(wordArr[i]).equals(dictS.get(wordArr[i]))) break;
                freq = dictWord.containsKey(wordArr[i])? dictWord.get(wordArr[i])+1 : 1;
                dictWord.put(wordArr[i], freq);

                while (!tmpS.isEmpty() && !tmpS.peek().equals(wordArr[i])) {
                    tmpS.pop();
                }
                if(tmpS.isEmpty()) break;
                if(i == 0 && tmpS.peek().equals(wordArr[i])) result++;
            }
        }
        return result;
    }


    private static int numValidSubseq(String s, String[] words) {
        int count = 0;

        // Check each word to see if it's a subsequence of s
        for (String word : words) {
            if (isSubsequence(s, word)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Helper method to check if target is a subsequence of source
     * @param source the main string
     * @param target the string to check as subsequence
     * @return true if target is a subsequence of source
     */
    private static boolean isSubsequence(String source, String target) {
        int sourceIndex = 0;
        int targetIndex = 0;

        // Use two pointers to traverse both strings
        while (sourceIndex < source.length() && targetIndex < target.length()) {
            // If characters match, move target pointer
            if (source.charAt(sourceIndex) == target.charAt(targetIndex)) {
                targetIndex++;
            }
            // Always move source pointer
            sourceIndex++;
        }

        // If we've matched all characters in target, it's a subsequence
        return targetIndex == target.length();
    }

    public static void main(String[] args) {
        String s = "abcde";
        String s2 = "dsahjpjauf";
        String[] words = {"a", "bb", "acd", "ace", "adc", "dce", "eab"};
        String[] words2 = {"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"};
        System.out.println(getNumOfSubSequence(s, words));
        System.out.println(getNumOfSubSequence(s2, words2));

        System.out.println(numValidSubseq(s, words));
        System.out.println(numValidSubseq(s2, words2));

    }
}
