package com.wil.practice.algo.dp;

public class MinDownRoad {


    private static int findDownRoad (int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if(m==1&&n==1) {
            return matrix[0][0];
        }
        int[][] paths = new int[2][n];
        for(int i =0; i<n; i++) {
            paths[0][i] = matrix[0][i];
        }
        int minPath = Integer.MAX_VALUE;
        for(int i=1; i<m; i++) {
            for(int j =0; j<n; j++) {
                if(j==0) {
                    paths[i%2][j] = Math.min(paths[(i-1)%2][j], paths[(i-1)%2][j+1]) + matrix[i][j];
                } else if(j==n-1) {
                    paths[i%2][j] = Math.min(paths[(i-1)%2][j-1], paths[(i-1)%2][j])+ matrix[i][j];
                } else {
                    paths[i%2][j] = Math.min(Math.min(paths[(i-1)%2][j-1], paths[(i-1)%2][j]), paths[(i-1)%2][j+1])+ matrix[i][j];
                }
                if(i==m-1) {
                    minPath = minPath>paths[i%2][j]?paths[i%2][j]:minPath;
                }
            }
        }
        return minPath;
    }



    private static int optimizedFindDownRoad (int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if(m==1&&n==1) {
            return matrix[0][0];
        }
        int[] paths = new int[n];
        for(int i =0; i<n; i++) {
            paths[i] = matrix[0][i];
        }
        int minPath = Integer.MAX_VALUE;
        for(int i=1; i<m; i++) {
            for(int j =0; j<n; j++) {
                if(j==0) {
                    paths[j] = Math.min(paths[j], paths[j+1]) + matrix[i][j];
                } else if(j==n-1) {
                    paths[j] = Math.min(paths[j-1], paths[j])+ matrix[i][j];
                } else {
                    paths[j] = Math.min(Math.min(paths[j-1], paths[j]), paths[j+1])+ matrix[i][j];
                }
                if(i==m-1) {
                    minPath = minPath>paths[j]?paths[j]:minPath;
                }
            }
        }
        return minPath;
    }

    public static void main(String[] args) {
        int[][] matrix = {{2,1,3}, {6,5,4}, {7,8,9}};
        System.out.println(findDownRoad(matrix));
        System.out.println(optimizedFindDownRoad(matrix));

    }

}
