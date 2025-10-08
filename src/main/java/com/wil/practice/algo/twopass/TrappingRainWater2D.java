package com.wil.practice.algo.twopass;

import java.util.PriorityQueue;

public class TrappingRainWater2D {

    private static int twoPassSolution(int[][] heightMap) {
        int sum = 0;
        int m = heightMap[0].length;
        int n = heightMap.length;
        int[][] resCol = new int[n][m];
        int[][] resRow = new int[n][m];
        int max;
        for (int r=0; r<m; r++) {
            max = heightMap[0][r];
            resCol[0][r] = 0;
            for (int i=1; i<n; i++) {
                resCol[i][r] = max;
                max = Math.max(max, heightMap[i][r]);
            }
            max = heightMap[n-1][r];
            resCol[n-1][r] = 0;
            for (int i=n-2; i>=0; i--) {
                resCol[i][r] = Math.min(resCol[i][r], max);
                max = Math.max(max, resCol[i][r]);
            }
        }

        for (int r=0; r<n; r++) {
            max = heightMap[r][0];
            resRow[r][0] = 0;
            for (int i=1; i<m; i++) {
                resRow[r][i] = max;
                max = Math.max(max, heightMap[r][i]);
            }
            max = heightMap[r][m-1];
            resRow[r][m-1] = 0;
            for (int i=m-2; i>=0; i--) {
                resRow[r][i] = Math.min(resRow[r][i], max);
                max = Math.max(max, resRow[r][i]);
            }
        }

        int res;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                res = Math.min(resRow[i][j], resCol[i][j]) - heightMap[i][j];
                if (res>0) sum+=res;
            }
        }
        return sum;
    }



    static class Cell {
        int row, col, height;

        Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    private static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int m = heightMap.length;
        int n = heightMap[0].length;

        // Priority queue to process cells from lowest to highest
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.height - b.height);
        boolean[][] visited = new boolean[m][n];

        // Add all boundary cells to the queue
        for (int i = 0; i < m; i++) {
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        for (int j = 1; j < n - 1; j++) {
            pq.offer(new Cell(0, j, heightMap[0][j]));
            pq.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }

        int water = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Process cells from lowest boundary inward
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();

            // Check all four neighbors
            for (int[] dir : directions) {
                int newRow = cell.row + dir[0];
                int newCol = cell.col + dir[1];

                // Check bounds and if already visited
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;

                    // Water level is determined by the current max height
                    // If neighbor is lower, water can be trapped
                    water += Math.max(0, cell.height - heightMap[newRow][newCol]);

                    // Add neighbor with updated height (water level or its own height, whichever is higher)
                    pq.offer(new Cell(newRow, newCol, Math.max(cell.height, heightMap[newRow][newCol])));
                }
            }
        }

        return water;
    }




    public static void main(String[] args) {
        int[][] heightMap = new int[][]{{1,4,3,1,3,2}, {3,2,1,3,2,4}, {2,3,3,2,3,1}};
        int[][] heightMap2 = new int[][]{{3,3,3,3,3}, {3,2,2,2,3}, {3,2,1,2,3}, {3,2,2,2,3}, {3,3,3,3,3}};
        System.out.printf("Max Water Trapped: %d%n", twoPassSolution(heightMap));
        System.out.printf("Max Water Trapped: %d%n", twoPassSolution(heightMap2));
        int[][] heightMap3 = new int[][] {{5, 5, 5, 1}, {5, 1, 1, 5}, {5,1,5,5}, {5,2,5,8}};
        System.out.printf("Max Water Trapped: %d%n", twoPassSolution(heightMap3));
        System.out.printf("Max Water Trapped: %d%n", trapRainWater(heightMap3));
    }
}
