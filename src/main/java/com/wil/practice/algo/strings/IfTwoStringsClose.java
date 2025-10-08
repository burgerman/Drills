package com.wil.practice.algo.strings;

import java.util.*;

public class IfTwoStringsClose {


    private static boolean priorQueueSolution(String word1, String word2) {
        if ((word1.length()<1 || word2.length()<1) || word1.length() != word2.length()) return false;
        Set<Character> chars = new HashSet<>(26, 1.0f);
        int[] charCount = new int[26];
        int[] charCount2 = new int[26];
        char c;
        for (int i =0; i<word1.length(); i++) {
            c = word1.charAt(i);
            chars.add(c);
            charCount[c-'a']++;
        }

        for (int i =0; i<word2.length(); i++) {
            c = word2.charAt(i);
            charCount2[c-'a']++;
            if (!chars.contains(c)) return false;
        }

        PriorityQueue<Integer> pq1 = new PriorityQueue<>((i1, i2) -> i2-i1);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((i1, i2) -> i2-i1);
        for (int i =0; i<word1.length(); i++) {
            c = word1.charAt(i);
            pq1.add(charCount[c-'a']);
            c = word2.charAt(i);
            pq2.add(charCount2[c-'a']);
        }

        while (!pq1.isEmpty() && !pq2.isEmpty()) {
            if (!pq1.poll().equals(pq2.poll())) return false;
        }
        return true;
    }

    private static boolean distributionSolution(String word1, String word2) {
        if ((word1.length()<1 || word2.length()<1) || word1.length() != word2.length()) return false;
        int[] charCount = new int[26];
        int[] charCount2 = new int[26];
        Set<Character> chars = new HashSet<>(26, 1.0f);
        Set<Character> chars2 = new HashSet<>(26, 1.0f);
        char c;
        for (int i =0; i<word1.length(); i++) {
            c = word1.charAt(i);
            charCount[c-'a']++;
            chars.add(c);
            c = word2.charAt(i);
            charCount2[c-'a']++;
            chars2.add(c);
        }
        if (!chars.equals(chars2)) return false;
//        for (int i = 0; i < 26; i++) {
//            if ((charCount[i] == 0 && charCount2[i] != 0) ||
//                    (charCount[i] != 0 && charCount2[i] == 0)) return false;
//        }

        Arrays.sort(charCount);
        Arrays.sort(charCount2);

        return Arrays.equals(charCount, charCount2);
    }


    private static boolean closeStrings(String word1, String word2) {
        if ((word1.length()<1 || word2.length()<1) || word1.length() != word2.length()) return false;
        Map<Character, Integer> counts = new HashMap<>(26, 1.0f);
        Map<Character, Integer> counts2 = new HashMap<>(26, 1.0f);
        char c;
        for (int i =0; i<word1.length(); i++) {
            c = word1.charAt(i);
            counts.put(c, counts.getOrDefault(c, 0) + 1);
            c = word2.charAt(i);
            counts2.put(c, counts2.getOrDefault(c, 0) + 1);
        }

        if (!counts.keySet().equals(counts2.keySet())) return false;

        List<Integer> times1 = new ArrayList<>(counts.values());
        List<Integer> times2 = new ArrayList<>(counts2.values());
        Collections.sort(times1);
        Collections.sort(times2);
        return times1.equals(times2);
    }


    public static void main(String[] args) {
        String word1 = "cabbba";
        String word2 = "abbccc";
        System.out.printf(" %b%n", closeStrings(word1, word2));
        System.out.printf(" %b%n", distributionSolution(word1, word2));
        System.out.printf(" %b%n", priorQueueSolution(word1, word2));

    }
}
