package com.wil.practice.interview;

import java.util.*;
import java.util.stream.Collectors;

public class AmazonDeviation {

    private static int optimizedSolution(String str) {
        int maxDeviation = 0;

        // Check all possible substrings
        for (int i = 0; i < str.length(); i++) {
            int[] freq = new int[26]; // Assuming lowercase letters
            int distinctChars = 0;

            for (int j = i; j < str.length(); j++) {
                char c = str.charAt(j);
                if (freq[c - 'a'] == 0) {
                    distinctChars++;
                }
                freq[c - 'a']++;

                // Find min and max frequencies
                int minFreq = Integer.MAX_VALUE;
                int maxFreq = 0;
                for (int k = 0; k < 26; k++) {
                    if (freq[k] > 0) {
                        minFreq = Math.min(minFreq, freq[k]);
                        maxFreq = Math.max(maxFreq, freq[k]);
                    }
                }

                int deviation = maxFreq - minFreq;
                maxDeviation = Math.max(maxDeviation, deviation);
            }
        }

        return maxDeviation;
    }


    private static int kadaneSolution(String str) {
        Set<Character> uniqueChars = str.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());

        int max = 0;
        int primaryFreq, secondaryFreq;
        boolean valid;
        for (char maxChar : uniqueChars) {
            for (char minChar : uniqueChars) {
                if (maxChar == minChar) continue;
                primaryFreq =0;
                secondaryFreq = 0;
                valid = false;
                for (int i=0; i<str.length(); i++) {
                    if(str.charAt(i) == maxChar) primaryFreq++;
                    if(str.charAt(i) == minChar) {
                        secondaryFreq++;
                        valid = true;
                    }

                    if(secondaryFreq>primaryFreq){
                        valid = false;
                        primaryFreq = 0;
                        secondaryFreq = 0;
                    }

                    if(valid) {
                        max = Math.max(max, primaryFreq-secondaryFreq);
                    }
                }

                primaryFreq =0;
                secondaryFreq = 0;
                valid = false;
                for (int i=str.length()-1; i>=0; i--) {
                    if(str.charAt(i) == minChar) primaryFreq++;
                    if(str.charAt(i) == maxChar) {
                        secondaryFreq++;
                        valid = true;
                    }

                    if(secondaryFreq>primaryFreq){
                        valid = false;
                        primaryFreq = 0;
                        secondaryFreq = 0;
                    }

                    if(valid) {
                        max = Math.max(max, primaryFreq-secondaryFreq);
                    }
                }
            }
        }
        return max;
    }

    private static int maxDeviationInSubStrings(String str) {
        int max=0;
        Map<String, Integer> dict = new HashMap<>();
        for(int i=0; i<str.length(); i++) {
            for(int j=i+1; j<str.length(); j++) {
                String subStr = str.substring(i, j);
                List<Character> list;
                Map<Character, Integer> charCount;
                List<Map.Entry<Character, Integer>> res;
                int deviation;
                if (!dict.containsKey(subStr)) {
                    list = subStr.chars().mapToObj(e->(char)e).collect(Collectors.toList());
                    charCount = list.stream().collect(Collectors.groupingBy(c->c, Collectors.counting()))
                            .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().intValue()));
                    res = charCount.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).collect(Collectors.toList());
                    deviation = res.get(res.size()-1).getValue()-res.get(0).getValue();
                    dict.put(subStr,deviation);
                    max = Math.max(max, deviation);
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        String s = "caaacbb";
        System.out.println(maxDeviationInSubStrings(s));
        System.out.println(optimizedSolution(s));
        System.out.println(kadaneSolution(s));
    }
}
