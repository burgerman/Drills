package com.wil.practice.algo.cp600.graph.bfs;

import java.util.ArrayDeque;
import java.util.Deque;


public class RottingOrangesBFS {

    private static final int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    /**
     * Multi-source BFS
     */
    private static int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        int numFresh = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j, 0});
                } else if(grid[i][j] == 1) {
                    numFresh++;
                }
            }
        }

        if (queue.size()<1 && numFresh >0) return -1;
        // no rotten orange
        if (queue.size()<1) return 0;

        boolean[][] visited = new boolean[m][n];
        int count = 0;
        int[] current;
        int row, col, val, nextRow, nextCol;

        while (!queue.isEmpty()) {
            current = queue.poll();
            row = current[0];
            col = current[1];
            val = current[2];
            if (grid[row][col] == 1) continue;

            count = Math.max(count, val);

            for (int[] dir : DIRS) {
                nextRow = row + dir[0];
                nextCol = col + dir[1];
                if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && !visited[nextRow][nextCol] && grid[nextRow][nextCol] == 1) {
                    visited[nextRow][nextCol] = true;
                    grid[nextRow][nextCol] = 2;
                    numFresh--;
                    queue.add(new int[]{nextRow, nextCol, val + 1});
                }
            }
        }
        return numFresh>0? -1 : count;
    }


    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid));
        char[] chars = {'H', 'e', 'l', 'l', 'o'};
        String s = String.valueOf(chars);
    }

}
