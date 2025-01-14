package com.wil.practice.algo.cp600.dp;

public class CoinCollection {
    private static int findRouteWithBarriers(int[][] board, int n, int m) {
        int[][] dp = new int[n][m];
        int[][] coins = new int[n][m];
        boolean[][] accessible = new boolean[n][m];

        dp[0][0] = 1;
        accessible[0][0] = true;
        coins[0][0] = board[0][0];
        for (int i = 1; i < m; i++) {
            accessible[i][0] = board[i][0] !=-1? true:false;
            accessible[i][0] =  accessible[i-1][0] && accessible[i][0];
            accessible[0][i] = board[0][i] !=-1? true:false;
            accessible[0][i] = accessible[0][i - 1] && accessible[0][i];
            dp[i][0] = accessible[i][0]?dp[i - 1][0]:0;
            dp[0][i] = accessible[0][i]?dp[0][i-1]:0;
            coins[i][0] = accessible[i][0]?coins[i - 1][0]+board[i][0]:0;
            coins[0][i] = accessible[0][i]?coins[0][i-1]+board[0][i]:0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                accessible[i][j] = board[i][j] != -1? true:false;
                accessible[i][j] = accessible[i][j] && (accessible[i-1][j] || accessible[i][j-1]);
                if(accessible[i][j]) {
                    if(accessible[i-1][j] && accessible[i][j-1]) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + 1;
                        coins[i][j] = Math.max(coins[i][j-1], coins[i-1][j]) + board[i][j];
                    }
                    else if(accessible[i-1][j]) {
                        dp[i][j] = dp[i-1][j];
                        coins[i][j] = coins[i-1][j] + board[i][j];
                    } else {
                        dp[i][j] = dp[i][j-1];
                        coins[i][j] = coins[i][j-1]+ board[i][j];
                    }
                } else {
                    dp[i][j] = 0;
                    coins[i][j] = 0;
                }
            }
        }
        System.out.println("Max Routes Num: " + dp[n-1][m-1]);
        System.out.println("Max Coins Num: " + coins[n-1][m-1]);
        return dp[n-1][m-1];
    }

    private static int getMaxCoin(int[][] board, int n, int m) {
        int[][] dp = new int[n][m];
        dp[0][0] = board[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + board[0][i];
            dp[i][0] = dp[i - 1][0] + board[i][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + board[i][j];
            }
        }
        return dp[n-1][m-1];
    }


    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        int[][] board = {{1,0,1,0},{0,1,1,0},{1,2,0,0},{0,0,1,0}};
        System.out.println(getMaxCoin(board, n, m));

        int[][] board2 = {{1,0,1,-1},{0,-1,1,0},{-1,6,0,1},{0,1,2,0}};
        System.out.println(findRouteWithBarriers(board2, n, m));
    }
}
