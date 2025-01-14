package com.wil.practice.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Knapsack01 {

    public static void main(String[] args) {
        int[] vals = {60, 35, 50, 30};
        int[] weights = {5, 3, 4, 2};
        int assignedWeight = 5;
        int n = vals.length;
        System.out.println(knapSack(vals, weights, assignedWeight));
        System.out.println(compressedKnapsack(vals, weights, assignedWeight));
        System.out.println(dfs(vals, weights, assignedWeight, n));
    }


    static int knapSack (int[] vals, int[] weights, int assignedWeight) {
        int[][] K = new int[vals.length+1][assignedWeight+1];
        for (int i=0; i<=vals.length; i++) {
            for(int w=0; w<=assignedWeight; w++) {
                if(i==0 || w ==0) {
                    K[i][w] = 0;
                } else if(weights[i-1]<= w) {
                    K[i][w] = Math.max(vals[i-1]+K[i-1][w-weights[i-1]], K[i-1][w]);
                } else {
                    K[i][w] = K[i-1][w];
                }
            }
        }
        return K[vals.length][assignedWeight];
    }

    static int compressedKnapsack (int[] vals, int[] weights, int assignedWeight) {
        int[] K = new int[assignedWeight+1];
        for (int i=1; i<=vals.length; i++) {
            for(int w=assignedWeight; w>=0; w--) {
                if(weights[i-1]<=assignedWeight && weights[i-1]<=w) {
                    K[w] = Math.max(vals[i-1]+K[w-weights[i-1]], K[w]);
                }
            }
        }
        return K[assignedWeight];
    }
    /**
     * recursive 记忆搜索法
     * @param vals
     * @param weights
     * @param assignedWeight
     * @param n
     * @return
     */
    static int dfs(int[] vals, int[] weights, int assignedWeight, int n) {
        if (n==0 || assignedWeight==0) {
            return 0;
        } else if (weights[n-1]>assignedWeight) {
            return dfs(vals, weights, assignedWeight, n-1);
        } else {
            return Math.max(vals[n-1] + dfs(vals, weights, assignedWeight-weights[n-1], n-1), dfs(vals, weights, assignedWeight, n-1));
        }
    }
}
