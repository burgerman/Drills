package com.wil.practice.algo.dp;

import javax.validation.constraints.Max;
import java.util.Arrays;

public class EggDropDp {
    private static int[][] dp;

    /**
     * dp[k][m] 指 k个鸡蛋，移动m次最多能确定多少层
     *  dp[k][m] 最多确定的楼层数为 F
     *  选择在i层楼起扔，则从i层起抛，可将F分为3段
     *  Right: dp[k][m-1] i层起抛的鸡蛋没碎
     *  Left: dp[k-1][m-1] i层起抛鸡蛋碎了
     *  ith floor
     *  formula: dp[k][m] = max(dp[k-1][m-1], dp[k][m-i]) + 1
     *
     *  Memorization
     */
    private static int eggDropMemo(int floor) {
        int egg = 2;
        int res = Integer.MAX_VALUE;
        dp = new int[egg+1][floor+1];

        for(int i=1; i<=egg; i++) {
            for(int j=1; j<=floor; j++){
                if(j==1) {
                    dp[i][j] = 1;
                    continue;
                } else if(i==1) {
                    dp[i][j] = j;
                    continue;
                } else {
                    dp[i][j] =res;
                    for(int x = 1; x<=j; x++) {
                        dp[i][j] = Math.min(Math.max(dp[i-1][x-1],dp[i][j-x])+1, dp[i][j]);
                    }
                }
            }
        }
        return dp[egg][floor];
    }


    /**
     * By given the num of floors and eggs, find out How many times we mostly can drop. Mind shifting-> Assume if now we have i shots for this trial with given num of eggs, how many floors we can deal with? And this leads to an equation: f(trial, egg) = floor;
     * We create an array to keep the value of the 'floor'.
     * e.g. 1 shot, 2 eggs -> arr[1][1] = 1; arr[1][2] = 1;
     * The final answer to the question is how many trials we need if there're 36 floors, which means what we need to find out is the value of variable 'trial' in this linear equation: f(trial, 2) = 36
     * As only one unknown is there, let's just put numbers starting from 0 and increasing by 1 into this equation one after another, the least number that turns out the value not less than 36 is the solution.
     *
     * Time complexity: O(N*floor)
     * Space complexity: O(N*floor)
     * @param floor
     * @return
     */

    private static int optimizedEggDropMemo(int floor) {
        int n = 2;
        // Store the max times of trials with available moves and eggs
        dp = new int[floor+1][n+1];
        int move = 0;
        // Find the least moves that turns out the value not less than 36
        while (dp[move][n] < floor) {
            move +=1;
            for(int x = 1; x<=n; x++) {
                // start from x-th floor: +1;
                // egg broken on x-th floor: dp[move-1][x-1];
                // egg not broken on x-th floor: dp[move-1][x];
                dp[move][x] = 1+dp[move-1][x-1]+dp[move-1][x];
            }
        }
        return move;
    }


    public static void main(String[] args) {
        System.out.println(optimizedEggDropMemo(36));
    }
}
