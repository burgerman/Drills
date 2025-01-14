package com.wil.practice.algo.cp600.dp;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    private static List<String> generateParenthesis(int n) {
        // Initialize a DP table
        List<List<String>> dp = new ArrayList<>();

        // Base case
        dp.add(new ArrayList<>());
        dp.get(0).add(""); // dp[0] contains an empty string

        // Fill dp table for 1 to n
        for (int i = 1; i <= n; i++) {
            List<String> currentList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                // Combine dp[j] and dp[i-j-1]
                for (String first : dp.get(j)) {
                    for (String second : dp.get(i - j - 1)) {
                        currentList.add("(" + first + ")" + second);
                    }
                }
            }
            dp.add(currentList);
        }

        return dp.get(n); // Return the list for dp[n]
    }

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        int n = 3;
        List<String> result = gp.generateParenthesis(n);
        System.out.println(result);
    }

}
