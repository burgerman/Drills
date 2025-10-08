package com.wil.practice.algo.dp;

import java.util.Arrays;

public class CrossBorder {
    private static final int valMod = (int)Math.pow(10, 9)+7;
    int m, n, availableMoves;

    public int accessiblePaths(int M, int N, int moves, int startX, int startY) {
        m = M;
        n = N;
        availableMoves = moves;
        int [][] paths = new int [m*n][availableMoves+1];

        for(int i=0; i<m; i++) {
            for(int j =0; j<n; j++) {
                if(i==0 || i==m-1 || j==0 || j==n-1) add(i, j, paths);
            }
        }

        for(int move=1; move <= availableMoves; move++) {
            for(int i=0; i<m*n; i++) {
                int x = map1Dto2D(i)[0];
                int y = map1Dto2D(i)[1];
                for(int[] dir : Directions.DIRS_ARR) {
                    int nextX = x+dir[0];
                    int nextY = y+dir[1];
                    if(nextX>=0 && nextX< m && nextY>=0 && nextY < n) {
                        paths[i][move] += paths[map2Dto1D(nextX, nextY)][move-1];
                        paths[i][move] %= valMod;
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(paths));
        return paths[map2Dto1D(startX, startY)][availableMoves];
    }

    void add (int x, int y, int[][] arr) {
        int index = map2Dto1D(x, y);
        for(int move =1; move<= availableMoves; move++) {
            arr[index][move]+=1;
        }
    }

    int map2Dto1D(int x, int y) {
        return x*n+y;
    }

    int[] map1Dto2D(int index) {
        return new int[]{index/n, index%n};
    }

    public static void main(String[] args){
        CrossBorder cb = new CrossBorder();
        int paths = cb.accessiblePaths(2, 2, 2, 0, 0);
        System.out.println(paths);
    }


}

enum Directions{
    DOWN(new int[]{1, 0}),
    UP(new int[]{-1,0}),
    FORWARD(new int[]{0,1}),
    BACK(new int[]{0,-1});

    public final int[] direct;

    Directions(int[] direct) {
        this.direct = direct;
    }

    //Move Directions
    public static final int[][] DIRS_ARR = new int[][]{DOWN.direct, UP.direct, FORWARD.direct, BACK.direct};
}
