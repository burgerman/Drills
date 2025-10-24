package com.wil.practice.algo.dp;

public class DominoTrominoTiling {

    private static final long MOD = 1000000007;



    private static int twoArraySolution(int n) {
        if (n <= 2) return n;
        long[] f = new long[n + 1]; // full board, using dominoes
        long[] g = new long[n + 1]; // half-filled board, using trominoes
        f[1] = 1;
        f[2] = 2;
        g[1] = 0;
        g[2] = 1;
        for (int i = 3; i <= n; i++) {
            g[i] = (g[i - 1] + f[i - 2])% MOD;  // half-filled with tromino
            f[i] = (f[i - 1] + f[i - 2] + 2 * g[i - 1])% MOD;  // full board with dominoes or trominoes
        }
        return (int)f[n];
    }

    private static int numTilings(int n) {
        if (n <= 2) return n;

        long[][] dp = new long[3][n + 1];

        dp[0][0] = 1;
        dp[0][1] = 1;

        for (int i = 2; i <= n; i++) {
            // Fully covered: can come from:
            // 1. Previous fully covered + vertical domino
            // 2. Two columns back fully covered + two horizontal dominoes
            // 3. Previous partially covered (top) + tromino filling bottom
            // 4. Previous partially covered (bottom) + tromino filling top
            dp[0][i] = (dp[0][i - 1] + dp[0][i - 2] + dp[1][i - 1] + dp[2][i - 1]) % MOD;

            // Top cell filled (bottom empty): can come from:
            // 1. Previous fully covered + tromino with top extending
            // 2. Previous bottom filled + horizontal domino on top
            dp[1][i] = (dp[0][i - 2] + dp[2][i - 1]) % MOD;

            // Bottom cell filled (top empty): can come from:
            // 1. Previous fully covered + tromino with bottom extending
            // 2. Previous top filled + horizontal domino on bottom
            dp[2][i] = (dp[0][i - 2] + dp[1][i - 1]) % MOD;
        }

        return (int) dp[0][n];
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println(numTilings(n));
        int n2 = 5;
        System.out.println(numTilings(n2));
    }
}
