package com.wil.practice.algo.dp;

public class FlippingMatrix {
    /**
     * DP Solution
     * @param matrix
     * @return
     */
    public static int flippingMatrix(int[][] matrix) {
        int n = matrix.length / 2;
        int max = 0;

        // For each position in the upper-left n×n submatrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Find the maximum value among the 4 possible positions
                // that could end up at position (i,j) after flipping
                int origin = matrix[i][j];                    // original
                int reverseRow = matrix[i][2*n - 1 - j];          // flip row
                int reverseCol = matrix[2*n - 1 - i][j];          // flip column
                int reverseBoth = matrix[2*n - 1 - i][2*n - 1 - j]; // flip both

                // Add the maximum of these 4 options to our sum
                max += Math.max(Math.max(origin, reverseBoth),
                        Math.max(reverseRow, reverseCol));
            }
        }

        return max;
    }

    public static void main(String[] args) {
        // Test case from the problem
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };

        System.out.println("Result: " + flippingMatrix(matrix)); // Should output 4

        // Larger test case
        int[][] matrix2 = {
                {112, 42, 83, 119},
                {56, 125, 56, 49},
                {15, 78, 101, 43},
                {62, 98, 114, 108}
        };

        System.out.println("Result: " + flippingMatrix(matrix2)); // Should output 414
    }

}
