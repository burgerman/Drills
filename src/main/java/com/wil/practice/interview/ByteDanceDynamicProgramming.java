package com.wil.practice.interview;

import java.util.Arrays;

/**
 * Dynamic programming
 */
public class ByteDanceDynamicProgramming {
    public static void main(String[] args) {
        int[][] arr = {{1,3,1}, {1, 4, 1}, {2, 4, 1}};
        int m = 0, n=0;
        for (int[] row : arr) {
            m = row.length;
            n++;
        }
        System.out.println(Arrays.deepToString(giftCollector(arr, m, n)));
    }

    public static int[][] giftCollector(int[][]arr, int m, int n) {
        int[][] records = new int[m][n];
        records[0][0] = arr[0][0];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++){
                if(i<1 && j-1>=0) {
                    records[i][j] = arr[i][j] + records[i][j-1];
                } else if(j<1 && i-1>=0) {
                    records[i][j] = arr[i][j] + records[i-1][j];
                } else if (i-1>=0 && j-1>=0){
                    records[i][j] = records[i-1][j]>records[i][j-1]? arr[i][j] + records[i-1][j]:arr[i][j]+records[i][j-1];
                } else {
                    continue;
                }
            }
        }
        return records;
    }




}
