package com.wil.practice.algo.strings;

import java.util.List;

public class GridChallenge {

    /**
     *
     * @param grid
     * @return "YES" or "NO"
     */
    private static String gridChallengeSolve(List<String> grid) {

        int m = grid.size();
        int n = grid.get(0).length();

        char[][] newGrid = new char[m][n];
        int index;
        for (int i=0; i<m; i++) {

            int[] count = new int[26];
            // char only ranges [a-z] each row
            for (char c : grid.get(i).toCharArray()) {
                count[c-'a']++;
            }
            index = 0;
            for (int j =0; j<26; j++) {
                while (count[j]>0) {
                    newGrid[i][index] = (char)('a'+j);
                    count[j]--;
                    index++;
                }
            }
        }

        char c1, c2;
        for (int i=1; i<m; i++) {
            for(int j=0; j<n; j++) {
                c1 = newGrid[i-1][j];
                c2 = newGrid[i][j];
                if(c1>c2) return "NO";
            }
        }
        return "YES";
    }


}
