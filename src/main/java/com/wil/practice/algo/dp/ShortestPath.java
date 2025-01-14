package com.wil.practice.algo.dp;

public class ShortestPath {
    public static int seekShortcut(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i>0 && j>0) {
                    grid[i][j] += grid[i-1][j]>grid[i][j-1]? grid[i][j-1]:grid[i-1][j];
                } else if(i<1 && j-1>=0) {
                    grid[i][j]=grid[i][j]+grid[i][j-1];
                } else if(j<1 && i-1>=0) {
                    grid[i][j]=grid[i][j]+grid[i-1][j];
                } else {
                    continue;
                }
            }
        }
        return grid[m-1][n-1];
    }



    public static void main(String[] args) {
        int[][] arr = {{1,3,1}, {1,5,1},{4,2,1}};
        System.out.println(seekShortcut(arr));
    }

}
