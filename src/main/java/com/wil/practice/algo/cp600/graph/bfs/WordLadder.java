package com.wil.practice.algo.cp600.graph.bfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {

    /**
     * Bi-directional BFS
     */
    private static int LadderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) return 0;

        Set<String> forwardSet = new HashSet<>();
        Set<String> backwardSet = new HashSet<>();
        Set<String> visited = new HashSet<>();

        forwardSet.add(beginWord);
        backwardSet.add(endWord);
        int len = 1;
        Set<String> tmp;
        while (!forwardSet.isEmpty() && !backwardSet.isEmpty()) {
            if (forwardSet.size() > backwardSet.size()) {
                tmp = forwardSet;
                forwardSet = backwardSet;
                backwardSet = tmp;
            }

            Set<String> nextLayer = new HashSet<>();
            for (String word : forwardSet) {
                char[] letters = word.toCharArray();
                for (int i=0; i<letters.length; i++) {
                    char current = letters[i];
                    for (char c = 'a'; c<='z'; c++) {
                        if (c == current) continue;
                        letters[i] = c;
                        String nextWord = String.valueOf(letters);
                        if (backwardSet.contains(nextWord)) {
                            return len+1;
                        }
                        if (dict.contains(nextWord) && !visited.contains(nextWord)) {
                            nextLayer.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                    letters[i] = current;
                }
            }
            forwardSet = nextLayer;
            len++;
        }

        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        System.out.println(LadderLength(beginWord, endWord, List.of(wordList)));
    }
}
