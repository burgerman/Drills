package com.wil.practice.algo.dp;

import java.util.Arrays;

public class Amazon_Interview {

    /**
     * Eight houses, represented as cells, are arranged in a straight line.
     * Each day every cell competes with its adjacent cells (neighbors).
     * An integer value of 1 represents an active cell and a value of O represents an inactive cell.
     * If the neighbors on both the sides of a cell are either active or inactive, the cell becomes inactive on the next day;
     * otherwise the cell becomes active. The two cells on each end have a single adjacent cell,
     * so assume that the unoccupied space on the opposite side is an inactive cell.
     * Even after updating the cell state, consider its previous state when updating the state of other cells.
     * The state information of all cells should be updated simultaneously.
     * Write an algorithm to output the state of the cells after the given number of days.
     */

    private static int[] cellCompute(int[] states, int days) {
        if(days<1) {
            return states;
        }
        int[][] dp = new int[days+1][states.length];
        dp[0] = states;
        for(int d=1; d<=days; d++) {
            for(int i=0; i<states.length; i++) {
                if(i==0) {
                    dp[d][i] = dp[d-1][i+1] != 1? 1:0;
                } else if (i==states.length-1) {
                    dp[d][i] = dp[d-1][i-1] != 1? 1:0;
                } else {
                    dp[d][i] = dp[d-1][i-1] != dp[d-1][i+1]? 0:1;
                }
            }
        }
        return dp[days];
    }

    private static int[] cellComputeInRollingArray(int[] states, int days) {
        if(days<1) {
            return states;
        }
        int[][] dp = new int[2][states.length];
        dp[0] = states;
        for(int d=1; d<=days; d++) {
            for(int i=0; i<states.length; i++) {
                if(i==0) {
                    dp[d%2][i] = dp[(d-1)%2][i+1] != 1? 1:0;
                } else if (i==states.length-1) {
                    dp[d%2][i] = dp[(d-1)%2][i-1] != 1? 1:0;
                } else {
                    dp[d%2][i] = dp[(d-1)%2][i-1] != dp[(d-1)%2][i+1]? 0:1;
                }
            }
        }
        return dp[days%2];
    }


    public static void main(String[] args) {
        int[] arr = {1,1,0,1};
        int days = 2;
        System.out.println(Arrays.toString(cellCompute(arr, days)));
        System.out.println(Arrays.toString(cellComputeInRollingArray(arr, days)));

    }


}
