package com.wil.practice.algo.cp600.graph.bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinKnightMove {


    /**
     *  Grid BFS
     */
    private static int findMinKnightMove(int x, int y) {
        int[][] dirs = {
                {1, 2}, {2, 1}, {-1, 2}, {-2, 1},
                {1, -2}, {2, -1}, {-1, -2}, {-2, -1}
        };

        x = Math.abs(x);
        y = Math.abs(y);

        if (x==0 && y == 0) return 0;

        Deque<int[]> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new int[]{0, 0, 0});
        visited.add("0,0");
        int row, col, move, nextRow, nextCol, nextMove;
        String coordinate;
        int[] current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            row = current[0];
            col = current[1];
            move = current[2];

            for (int[] dir : dirs) {
                nextRow = row + dir[0];
                nextCol = col + dir[1];
                coordinate = nextRow+","+nextCol;
                if (nextRow >= -2 && nextCol >= -2 && !visited.contains(coordinate)) {
                    if (nextRow == x && nextCol == y) return move + 1;
                    visited.add(coordinate);
                    queue.add(new int[] {nextRow, nextCol, move+1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int x = -3, y = -3;
        int x1 = -5, y1 = 5;
        int x2 = 2, y2 = 4;
        System.out.println(findMinKnightMove(x, y));
        System.out.println(findMinKnightMove(x1, y1));
        System.out.println(findMinKnightMove(x2, y2));
    }

}
