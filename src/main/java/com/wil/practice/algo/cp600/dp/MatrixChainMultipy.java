package com.wil.practice.algo.cp600.dp;

public class MatrixChainMultipy {

    /**
     * Bottom-up solution
     */
    public static int matrixChainOrder(int[] dimensions) {
        int n = dimensions.length - 1;
        // minimum multiplications needed from i to j
        int[][] dp = new int[n + 1][n + 1];

        // chain length (2, 3, 4, ..., n)
        for (int len = 2; len <= n; len++) {
            // All possible starting positions for this length
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1; // ending position

                dp[i][j] = Integer.MAX_VALUE;

                // Try all possible split points k between i and j
                for (int k = i; k < j; k++) {
                    // left_subproblem + right_subproblem + cost_to_multiply_results
                    //Every matrix Aᵢ has dimension: dimensions[i-1] × dimensions[i]
                    // Left subchain dimensions: A[i..k] -> dimensions[i−1] × dimensions[k]
                    // Right subchain dimensions: A[k+1..j] -> dimensions[k] × dimensions[j]
                    // Num of multiplications: dimensions[i - 1] * dimensions[k] * dimensions[j]
                    int num = dp[i][k] + dp[k + 1][j] + dimensions[i - 1] * dimensions[k] * dimensions[j];

                    dp[i][j] = Math.min(dp[i][j], num);
                }
            }
        }
        return dp[1][n];
    }


    public static void main(String[] args) {
        // Matrix 1: 40x20, Matrix 2: 20x30, Matrix 3: 30x10, Matrix 4: 10x30
        int[] dimensions = {40, 20, 30, 10, 30};

        System.out.println("Matrix dimensions:");
        for (int i = 0; i < dimensions.length - 1; i++) {
            System.out.println("Matrix " + (i + 1) + ": " + dimensions[i] + "x" + dimensions[i + 1]);
        }

        int minCost = matrixChainOrder(dimensions);
        System.out.println("\nMin multiplications needed: " + minCost);
    }

}
