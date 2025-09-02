package com.wil.practice.algo.cp600.dp;

public class EditDistance {
    public static int editDistance(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        int m = s1.length();
        int n = s2.length();

        if (m == 0) return n;
        if (n == 0) return m;

        // Use shorter string as column to minimize space
        if (m > n) {
            return editDistance(s2, s1);
        }

        // Scrolling array
        // prev represents the previous row, curr represents the current row
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        // Initialize first row (transforming empty string to s2 prefix)
        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }

        // Fill the DP table row by row
        for (int i = 1; i <= m; i++) {
            curr[0] = i; // Cost of transforming s1 prefix to empty string

            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // Characters match
                    curr[j] = prev[j - 1];
                } else {
                    // Characters don't match, take minimum:
                    // 1. Insert: curr[j-1] + 1
                    // 2. Delete: prev[j] + 1
                    // 3. Replace: prev[j-1] + 2
                    curr[j] = Math.min(
                            Math.min(curr[j - 1], prev[j]) + 1,
                            prev[j - 1] + 2
                    );
                }
            }

            // Swap arrays for next iteration (scrolling effect)
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }


    public static void main(String[] args) {
        String s1 = "analytics";
        String s2 = "analysis";
        int dis =  editDistance(s1, s2);
        System.out.printf("String Distance: %d%n", dis);
    }
}
