package com.wil.practice.algo.cp600.backtracking;

public class MaxScoreWords {
    private static int maxScoreWords(String[] words, char[] letters, int[] score) {
        // Count available letters
        int[] letterCount = new int[26];
        for (char c : letters) {
            letterCount[c - 'a']++;
        }
        return dfs(words, 0, letterCount, score);
    }


    private static int dfs(String[] words, int index, int[] letterCount, int[] score) {
        // Base case: processed all words
        if (index == words.length) {
            return 0;
        }

        // Option 1: Skip current word
        int maxScore = dfs(words, index + 1, letterCount, score);

        // Option 2: Try to include current word
        String word = words[index];
        int[] wordLetterCount = new int[26];
        int wordScore = 0;

        // Count letters needed for current word and calculate its score
        for (char c : word.toCharArray()) {
            wordLetterCount[c - 'a']++;
            wordScore += score[c - 'a'];
        }

        // Check if we have enough letters to form this word
        boolean canForm = true;
        for (int i = 0; i < 26; i++) {
            if (wordLetterCount[i] > letterCount[i]) {
                canForm = false;
                break;
            }
        }

        if (canForm) {
            // Use the letters (backtrack step)
            for (int i = 0; i < 26; i++) {
                letterCount[i] -= wordLetterCount[i];
            }

            // Recursively solve for remaining words
            int scoreWithCurrentWord = wordScore + dfs(words, index + 1, letterCount, score);
            maxScore = Math.max(maxScore, scoreWithCurrentWord);

            // Restore the letters (backtrack)
            for (int i = 0; i < 26; i++) {
                letterCount[i] += wordLetterCount[i];
            }
        }

        return maxScore;
    }


    public static void main(String[] args) {
        String[] words = {"dog","cat","dad","good"};
        char[] letters = {'a', 'a','c','d','d','d','g','o','o'};
        int[] score = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println(maxScoreWords(words, letters, score));
    }
}
