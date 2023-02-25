package com.wil.practice.algo.dp;

public class MaximalSquare {


    private static int maxSquare(int[][] matrix) {
        if(matrix.length<1 || matrix[0].length<1) {
            return 0;
        }

        int length = matrix.length;
        int width =  matrix[0].length;
        int[][] arr = new int[length+1][width+1];
        int maxSize = 0;

        for (int i=1; i<=length; i++) {
            for(int j=1; j<=width; j++) {
                if(matrix[i-1][j-1] == 1) {
                    arr[i][j] = Math.min(arr[i-1][j], Math.min(arr[i-1][j-1], arr[i][j-1])) + 1;
                    maxSize = Math.max(maxSize, arr[i][j]);
                }
            }
        }
        return maxSize*maxSize;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,0,1,0,0},
                {1,0,1,1,1},
                {1,1,1,1,1},
                {1,0,0,1,0}};
        System.out.println(maxSquare(matrix));
    }
}
