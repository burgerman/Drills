package com.wil.practice.algo.cp600.dp;

public class CherryPick {


    /**
     * DFS + memorized dp solution
     * Two people walk through paths from top left to bot right at the same time
     * Both take the same steps (paths can differ, but num of steps are same)
     */
    private static int pickCherry(int[][] grid) {
        int m = grid.length;

        Integer[][][] memo = new Integer[m][m][m];
        return Math.max(0, dfs(grid, memo, m, 0, 0, 0));
    }

    private static int dfs(int[][]grid, Integer[][][] memo, int n, int row1, int col1, int row2) {
        int col2 = row1+col1-row2;

        if (row1 >= n || col1 >= n || row2 >= n || col2 >= n
                || grid[row1][col1] == -1 || grid[row2][col2] == -1)
            return Integer.MIN_VALUE;

        if (row1 == n-1 && col1 == n-1) return grid[row1][col1];

        if (memo[row1][col1][row2] != null) return memo[row1][col1][row2];

        int count = grid[row1][col1];
        if (row1!=row2 || col1!=col2) count += grid[row2][col2];
        // both down; one down one right; one right one down; both right
        int rest = Math.max(Math.max(dfs(grid, memo, n, row1+1, col1, row2+1), dfs(grid, memo, n, row1+1, col1, row2)),
                Math.max(dfs(grid, memo, n, row1, col1+1, row2+1), dfs(grid, memo, n, row1, col1+1, row2)));

        count += rest;
        memo[row1][col1][row2] = count;
        return count;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, -1},
                {1, 0, -1},
                {1, 1,  1}
        };
        System.out.println(pickCherry(grid)); // Output: 5
    }



}
