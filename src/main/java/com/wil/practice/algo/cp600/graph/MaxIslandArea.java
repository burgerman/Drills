package com.wil.practice.algo.cp600.graph;

public class MaxIslandArea {

    private static int dfs(int[][] grid, boolean[][] visited, int row, int col, int m, int n) {
        if(row<0 || col<0 || row>=m || col>=n || grid[row][col] == 0 || visited[row][col]) return 0;
        visited[row][col] = true;
        int islandArea = 1;
        islandArea+=dfs(grid, visited, row+1, col, m, n);
        islandArea+=dfs(grid, visited, row, col+1, m, n);
        islandArea+=dfs(grid, visited,row-1, col, m, n);
        islandArea+=dfs(grid, visited, row, col-1, m, n);
        return islandArea;
    }

    private static int findMaxArea(int[][] grid, int m, int n) {
        int maxArea = 0;
        boolean[][] visited = new boolean[m][n];
        int area;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    area = dfs(grid, visited, i, j, m, n);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {

        int m = 4;
        int n = 4;
        int[][] grid = new int[][]{{0,0,0,0}, {0,1,1,0}, {0,1,0,0}, {0,1,0,1}};
        int maxArea = findMaxArea(grid, m, n);
        System.out.println("Max area of an island:" + maxArea);
    }

}
